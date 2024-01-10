package es.consumo.junta_arbitral.modules.document.service.impl;


import es.consumo.junta_arbitral.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.junta_arbitral.modules.document.model.entity.DocumentEntity;
import es.consumo.junta_arbitral.commons.db.repository.JJAARepository;
import es.consumo.junta_arbitral.commons.service.EntityCrudService;
import es.consumo.junta_arbitral.commons.service.ReadService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author serikat
 */
@Service
public class DocumentServiceImpl extends EntityCrudService<DocumentEntity, Long> {
    @Value("${path.documentos}")
    private String repoPath;
    private final ReadService<ArbitrationEntity, Long> arbitrationService;

    @Autowired
    public DocumentServiceImpl(JJAARepository<DocumentEntity, Long> repository,
                               ReadService<ArbitrationEntity, Long> arbitrationService) {
        super(repository);
        this.arbitrationService = arbitrationService;
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
        boolean exits = arbitrationService.exitsById(payload.getArbitrationId());
        if (!exits) {
            throw new RuntimeException("solictud arbitrasl no existe");
        }
        payload.setCreateAt(LocalDateTime.now());
        DocumentEntity entity = super.performCreate(payload);
        try {
            String path = updLoadFile(entity.getArbitrationId(), entity.getName(), payload.getBase64());
            entity.setPath(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return repository.save(entity);
    }


    private String updLoadFile(long arbitrationId, String name, String base64) throws IOException {
        Path repo = getFilePath(arbitrationId);

        if ((Files.notExists(repo))) {
            Files.createDirectories(repo);
        }
        String md5 = DigestUtils.md5Hex(name).toUpperCase();
        Path file = Path.of(repo.toString(), "/", md5);
        Files.deleteIfExists(file);
        Path create = Files.createFile(file);
        Files.write(create, base64.getBytes());

        return md5;
    }

    private Path getFilePath(long arbitrationId) {
        return Path.of(repoPath, "/arbitration/attachment/", String.valueOf(arbitrationId));
    }

    private String getFile(DocumentEntity documentEntity) throws IOException {
        Path file = Path.of(getFilePath(documentEntity.getArbitrationId()).toString(), "/", documentEntity.getPath());
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            return reader.lines().collect(Collectors.joining());
        }
    }
}
