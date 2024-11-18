package es.consumo.gescom.modules.document.service.impl;


import es.consumo.gescom.commons.db.entity.StatefulEntity;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.document.model.criteria.DocumentCriteria;
import es.consumo.gescom.modules.document.model.entity.DocumentEntity;
import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import es.consumo.gescom.commons.service.ReadService;
import es.consumo.gescom.modules.document.repository.DocumentRepository;
import es.consumo.gescom.modules.document.service.DocumentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author serikat
 */
@Service
public class DocumentServiceImpl extends EntityCrudService<DocumentEntity, Long> implements DocumentService {
    @Value("${path.document}")
    private String repoPath;
    private final ReadService<CampaignEntity, Long> campaingService;

    @Autowired
    private DocumentRepository documentRepository;


    @Autowired
    public DocumentServiceImpl(GESCOMRepository<DocumentEntity, Long> repository,
                               ReadService<CampaignEntity, Long> campaignService) {
        super(repository);
        this.campaingService = campaignService;
    }

    @Override
    public Optional<DocumentEntity> findById(Long id) {
        Optional<DocumentEntity> optional = super.findById(id);
        if (optional.isPresent()) {
            DocumentEntity document = optional.get();
            try {
                document.setBase64(getFile(document));
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return optional;
    }

    @Override
    protected DocumentEntity performCreate(DocumentEntity payload) {
        boolean exits = campaingService.exitsById(payload.getCampaignId());
        if (!exits) {
            throw new RuntimeException("solictud campaña no existe");
        }
        payload.setCreateAt(LocalDateTime.now());
        DocumentEntity entity = super.performCreate(payload);
        try {
            String path = updLoadFile(payload);
            entity.setPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return repository.save(entity);
    }


    private String updLoadFile(DocumentEntity payload) throws IOException {
        Path repo = getAbsoluteFilePath(payload);
        Path relativePath = getRelativeFilePath(payload);
        byte[] decodedBytes = Base64.getDecoder().decode(payload.getBase64());
        if ((Files.notExists(repo))) {
            Files.createDirectories(repo);
        }
        Path file = Path.of(repo.toString(), "/", payload.getName());
        //String md5 = DigestUtils.md5Hex(file.toString()).toUpperCase();
        Files.deleteIfExists(file);
        Path create = Files.createFile(file);
        Files.write(create, decodedBytes);

        return relativePath.toString();
    }

    private Path getRelativeFilePath(DocumentEntity payload) {
        String basePath = "/campaign/attachment/";
        String folderName = "";

        Long id = payload.getDocumentType().getId();
        if (id == 1) {
            folderName = String.valueOf(payload.getCampaignId());
        } else if (id == 2) {
            folderName = payload.getCampaignId() + "/DOC_PLAN";
        } else if (id == 3) {
            folderName = payload.getCampaignId() + "/FICHA_TRANSPARENCIA";
        } else {
            throw new IllegalArgumentException("Tipo de documento no soportado");
        }

        return Path.of(basePath, folderName);
    }

    private Path getAbsoluteFilePath(DocumentEntity payload) {
        return Path.of(repoPath, getRelativeFilePath(payload).toString());
    }

    private String getFile(DocumentEntity documentEntity) throws IOException {
        Path file = Path.of(repoPath,documentEntity.getPath(), "/", documentEntity.getName());
        return Base64.getEncoder().encodeToString(Files.readAllBytes(file));
    }

    @Override
    public Page<DocumentEntity> findDocumentByCampaignId(CriteriaWrapper<DocumentCriteria> wrapper, Long idCampaign) {
        return ((DocumentRepository) repository).findDocumentByCampaignId(wrapper.getCriteria().toPageable(), idCampaign);

    }

    @Override
    protected void performDelete(DocumentEntity data) {
        data.setState(2);
        repository.save(data);
    }
}
