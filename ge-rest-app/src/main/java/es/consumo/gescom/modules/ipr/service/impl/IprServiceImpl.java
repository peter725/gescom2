package es.consumo.gescom.modules.ipr.service.impl;

import es.consumo.gescom.modules.campaign.model.dto.QuestionsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.SearchDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.repository.CampaignProductServiceRepository;
import es.consumo.gescom.modules.ipr.model.converter.IprConverter;
import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.dto.IprResponseDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.iprQuestion.model.converter.IprQuestionConverter;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.repository.IprQuestionRepository;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.productServices.repository.ProductServiceRepository;
import es.consumo.gescom.modules.protocol.model.converter.ProtocolConverter;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsResponseDTO;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.questions.model.converter.QuestionsConverter;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import es.consumo.gescom.modules.questions.repository.QuestionsRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class IprServiceImpl extends EntityCrudService<IprEntity, Long> implements IprService {
    protected IprServiceImpl(GESCOMRepository<IprEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private IprRepository iprRepository;

    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private CampaignProductServiceRepository campaignProductServiceRepository;

    @Autowired
    private IprConverter iprConverter;

    @Autowired
    private IprQuestionRepository iprQuestionRepository;

    @Autowired
    private ProtocolConverter protocolConverter;

    @Autowired
    private IprQuestionConverter iprQuestionConverter;

    @Autowired
    private QuestionsConverter questionsConverter;

    @Autowired
    private ProductServiceRepository productServiceRepository;


    @Override
    public Page<IprEntity.SimpleProjection> findAllIprById(CriteriaWrapper<IprCriteria> wrapper, Long id) {
        return ((IprRepository) repository).findAllIprById(wrapper.getCriteria().toPageable(), id);
    }

    @Override
    public IprDTO createIPR(IprDTO payload) {
        IprEntity iprEntity =  iprConverter.convertToEntity(payload);
        IprEntity iprEntitySave = iprRepository.save(iprEntity);

        List<IprQuestionDTO> iprQuestionDTOList = payload.getIprQuestionDTOList();
        iprQuestionDTOList.forEach(iprQuestion -> {
            IprQuestionEntity iprQuestionEntity = new IprQuestionEntity();
            iprQuestionEntity.setCode(iprQuestion.getCode());
            iprQuestionEntity.setIprCode(iprQuestion.getIprCode());
            iprQuestionEntity.setOrderQuestion(iprQuestion.getOrderQuestion());
            iprQuestionEntity.setPercentageRespectTo(iprQuestion.getPercentageRespectTo());
            iprQuestionEntity.setFormula(iprQuestion.getFormula());
            iprQuestionEntity.setQuestion(iprQuestion.getQuestion());
            iprQuestionEntity.setIprId(iprEntitySave);
            iprQuestionEntity.setCreatedAt(LocalDateTime.now());

            iprQuestionRepository.save(iprQuestionEntity);
        });

        IprDTO iprDTONew = iprConverter.convertToModel(iprEntitySave);
        iprDTONew.setIprQuestionDTOList(iprQuestionDTOList);


        return iprDTONew;
    }

    @Override
    public IprDTO updateIpr(Long id, IprDTO payload) {
        IprEntity iprEntity = iprConverter.convertToEntity(payload);
        IprEntity oldEntity = iprRepository.findById(id).orElse(null);
        assert oldEntity != null;
        iprEntity.setCreatedAt(oldEntity.getCreatedAt());
        iprEntity.setUpdatedAt(oldEntity.getUpdatedAt());
        iprEntity.setState(oldEntity.getState());

        final List<Long> toDelete = new ArrayList<>();

        // Verifica si el ID es nulo o si la entidad convertida no tiene ID.
        if (iprEntity.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID del es requerido para la actualización.");
        }

        IprEntity iprSave = iprRepository.save(iprEntity);

        if (payload.getCode() != null) {
            List<IprQuestionEntity> iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprCode(iprSave.getCode());
            iterateAndDeletedIprQuestions(iprQuestionEntities, toDelete);
        }else{
            List<IprQuestionEntity> iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprId(iprSave.getId());
            iterateAndDeletedIprQuestions(iprQuestionEntities, toDelete);
        }

        if (!toDelete.isEmpty())
            iprQuestionRepository.deleteAllById(toDelete);

        List<IprQuestionDTO> iprQuestionDTOS = payload.getIprQuestionDTOList();
        iprQuestionDTOS.forEach(iprQuestionDTO ->{
            IprQuestionEntity iprQuestionEntity = new IprQuestionEntity();
            iprQuestionEntity.setCode(iprQuestionDTO.getCode());
            iprQuestionEntity.setIprCode(iprQuestionDTO.getIprCode());
            iprQuestionEntity.setOrderQuestion(iprQuestionDTO.getOrderQuestion());
            iprQuestionEntity.setPercentageRespectTo(iprQuestionDTO.getPercentageRespectTo());
            iprQuestionEntity.setFormula(iprQuestionDTO.getFormula());
            iprQuestionEntity.setQuestion(iprQuestionDTO.getQuestion());
            iprQuestionEntity.setIprId(iprEntity);

            iprQuestionRepository.save(iprQuestionEntity);
        });

        return iprConverter.convertToModel(iprSave);
    }

    private void iterateAndDeletedIprQuestions(List<IprQuestionEntity> iprQuestionsEntities, List<Long> toDelete) {

        for(IprQuestionEntity iprQuestionsEntity : iprQuestionsEntities){
            if(iprQuestionsEntity.getId() != (0)){
                if(Objects.nonNull(toDelete)){
                    toDelete.add(iprQuestionsEntity.getId());
                }
            }
        }
    }



    @Override
    public IprDTO findIprDTOById(Long id) {
        IprEntity iprEntity = super.repository.findById(id).orElseThrow();
        IprDTO iprDTO = iprConverter.convertToModel(iprEntity);
        iprDTO.setNameCampaign(campaignRepository.findById(iprEntity.getCampaignId()).get().getNameCampaign());
        iprDTO.setYear(campaignRepository.findById(iprEntity.getCampaignId()).get().getYear());
        ProtocolDTO protocolDTO;
        if (iprEntity.getProtocolCode() != null){
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolByCode(iprEntity.getProtocolCode()));
        }else{
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolById(iprEntity.getProtocolId()));
        }
        iprDTO.setProtocol(protocolDTO);
        iprDTO.setProtocolName(protocolDTO.getName());

        List<IprQuestionEntity> iprQuestionEntities = new ArrayList<>();
        if (iprDTO.getCode() == null){
            iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprId(iprDTO.getId());
        }else{
            iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprCode(iprDTO.getCode());
        }
        List<IprQuestionDTO> iprQuestionsDTOS = iprQuestionConverter.convertToModel(iprQuestionEntities);
        iprDTO.setIprQuestionDTOList(iprQuestionsDTOS);


        return iprDTO;
    }

    @Override
    public List<IprDTO> findAllIprByCampaignIdAndProtocolCode(Long campaignId, String protocolCode) {
        new ResultsResponseDTO();
        ResultsResponseDTO resultsResponseDTO;
        SearchDTO searchDTO = new SearchDTO();
        List<IprDTO> iprDTOS = iprConverter.convertToModel(iprRepository.findAllByCampaignAndProtocolCode(campaignId, protocolCode));
        List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(campaignId);
        searchDTO.setCampaignId(campaignId);
        searchDTO.setProtocolCode(protocolCode);

        for (IprDTO iprDTO : iprDTOS) {

            for (CampaignProductServiceEntity campaignProductServiceEntity : campaignProductServiceEntities) {

                searchDTO.setProductServiceId(campaignProductServiceEntity.getProductServiceId());
                searchDTO.setProductServiceCode(campaignProductServiceEntity.getCodeProductService());
                searchDTO.setIprCode(iprDTO.getCode());
                resultsResponseDTO = getResultsIpr(searchDTO);
                iprDTO.setResultsResponseDTO(resultsResponseDTO);

            }
        }
        return iprDTOS;
    }

    @Override
    public List<IprDTO> findAllIprByCampaignIdAndProtocolId(Long campaignId, Long protocolId) {
        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
        SearchDTO searchDTO = new SearchDTO();
        List<IprDTO> iprDTOS = iprConverter.convertToModel(iprRepository.findAllByCampaignAndProtocolId(campaignId, protocolId));
        List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(campaignId);
        searchDTO.setCampaignId(campaignId);
        searchDTO.setProtocolId(protocolId);

        for (IprDTO iprDTO : iprDTOS) {
            if (iprDTO.getCode() != null) {
                iprDTO.setIprQuestionDTOList(getAllQuestionsByIprCode(iprDTO.getCode()));
            }else{
                iprDTO.setIprQuestionDTOList(getAllQuestionsByIprId(iprDTO.getId()));
            }

            for (CampaignProductServiceEntity campaignProductServiceEntity : campaignProductServiceEntities) {

                searchDTO.setProductServiceId(campaignProductServiceEntity.getProductServiceId());
                searchDTO.setProductServiceCode(campaignProductServiceEntity.getCodeProductService());
                searchDTO.setIprCode(iprDTO.getCode());
                searchDTO.setIprId(iprDTO.getId());
                resultsResponseDTO = getResultsIpr(searchDTO);
                iprDTO.setResultsResponseDTO(resultsResponseDTO);

            }
        }
        return iprDTOS;
    }

    @Override
    public ResultsResponseDTO getResultProtocol(SearchDTO searchDTO) {
        ResultsResponseDTO resultsResponseDTOS = new ResultsResponseDTO();
        List<QuestionsResponseDTO> questionsResponseDTOS = new ArrayList<>();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        List<IprResponseDTO> iprResponseDTOS = new ArrayList<>();
        List<ProtocolResultsResponseDTO> protocolResultsResponseDTOS = new ArrayList<>();
        if (searchDTO.getProductServiceCode() != null) {
            ProductServiceEntity productServiceEntity = productServiceRepository.findProductServiceByCode(searchDTO.getProductServiceCode());
            if (searchDTO.getProductServiceCode() != null && productServiceEntity.getName() != null) {
                resultsResponseDTOS.setProductName(productServiceEntity.getCode().concat(" - ").concat(productServiceEntity.getName()));
            } else if (searchDTO.getProductServiceCode() != null) {
                resultsResponseDTOS.setProductName(productServiceEntity.getCode());
            }else {
                resultsResponseDTOS.setProductName(productServiceEntity.getName());
            }
        }else {
            ProductServiceEntity productServiceEntity = productServiceRepository.findProductServiceById(searchDTO.getProductServiceId());
            if (searchDTO.getProductServiceId() != null && productServiceEntity.getName() != null) {
                resultsResponseDTOS.setProductName(productServiceEntity.getCode().concat(" - ").concat(productServiceEntity.getName()));
            }
        }
        new ProtocolDTO();
        ProtocolDTO  protocolDTO;

        CampaignEntity campaignEntity = campaignRepository.findById(searchDTO.getCampaignId()).orElseThrow();
        if (searchDTO.getProtocolCode() != null) {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolByCode(searchDTO.getProtocolCode()));
        }else {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolById(searchDTO.getProtocolId()));
        }

        if (searchDTO.getProtocolCode() != null) {
            questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolCode(searchDTO.getProtocolCode()));
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getProductServiceCode());
        }else {
            questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolId(searchDTO.getProtocolId()));
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId(), searchDTO.getProductServiceCode());

        }

        resultsResponseDTOS.setCampaignName(campaignEntity.getNameCampaign());
        resultsResponseDTOS.setProtocolName(protocolDTO.getName());




        for (QuestionsDTO questionsDTO : questionsDTOS){

            QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
            String question = questionsDTO.getQuestion();
            String questionText = question != null ? question : "null"; // Si question es null, usa "null", de lo contrario, usa el valor de question
            questionsResponseDTO.setQuestion(questionText);
            questionsResponseDTO.setResponse(questionsDTO.getResponse());
            questionsResponseDTO.setOrderQuestion(questionsDTO.getOrderQuestion());

            for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), questionsDTO.getOrderQuestion().toString())) {
                    if (protocolResultsResponseDTO.getCcaaRes() != null) {
                        questionsResponseDTO.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
                    }
                    if (protocolResultsResponseDTO.getCcaaRen() != null) {
                        questionsResponseDTO.addToNumResponseNo(protocolResultsResponseDTO.getCcaaRen());
                    }
                    if (protocolResultsResponseDTO.getCcaaRep() != null) {
                        questionsResponseDTO.addToNumResponseNoProcede(protocolResultsResponseDTO.getCcaaRep());
                    }
                    questionsResponseDTO.setCodeQuestion(questionsDTO.getCodeQuestion());
                }
            }
            questionsResponseDTOS.add(questionsResponseDTO);

        }

        // Paso 1: Encontrar el valor máximo de orderQuestion en la lista actual
        int maxOrderQuestion = questionsResponseDTOS.stream()
                .mapToInt(QuestionsResponseDTO::getOrderQuestion)
                .max()
                .orElse(0); // Usamos orElse(0) para manejar el caso de una lista vacía

        QuestionsResponseDTO questionsResponseDTODC1 = new QuestionsResponseDTO();
        questionsResponseDTODC1.setQuestion("Nro. de establecimientos existentes");
        questionsResponseDTODC1.setOrderQuestion(maxOrderQuestion+1);
        questionsResponseDTODC1.setCodeQuestion("DC1");
        for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC1")) {
                questionsResponseDTODC1.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
            }
        }
        questionsResponseDTOS.add(questionsResponseDTODC1);
        maxOrderQuestion++;

        QuestionsResponseDTO questionsResponseDTODC8 = new QuestionsResponseDTO();
        questionsResponseDTODC8.setQuestion("Nro. de establecimientos controlados");
        questionsResponseDTODC8.setOrderQuestion(maxOrderQuestion+1);
        questionsResponseDTODC8.setCodeQuestion("DC8");
        for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC8")) {
                questionsResponseDTODC8.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
            }
        }
        questionsResponseDTOS.add(questionsResponseDTODC8);
        maxOrderQuestion++;

        QuestionsResponseDTO questionsResponseDTODC9 = new QuestionsResponseDTO();
        questionsResponseDTODC9.setQuestion("Total de productos/servicios controlados");
        questionsResponseDTODC9.setOrderQuestion(maxOrderQuestion+1);
        questionsResponseDTODC9.setCodeQuestion("DC9");
        for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC9")) {
                questionsResponseDTODC9.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
            }
        }
        questionsResponseDTOS.add(questionsResponseDTODC9);
        maxOrderQuestion++;

        QuestionsResponseDTO questionsResponseDTODC10 = new QuestionsResponseDTO();
        questionsResponseDTODC10.setQuestion("Total de productos/servicios correctos");
        questionsResponseDTODC10.setOrderQuestion(maxOrderQuestion+1);
        questionsResponseDTODC10.setCodeQuestion("DC10");
        for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC10")) {
                questionsResponseDTODC10.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
            }
        }
        questionsResponseDTOS.add(questionsResponseDTODC10);
        maxOrderQuestion++;

        QuestionsResponseDTO questionsResponseDTODC11 = new QuestionsResponseDTO();
        questionsResponseDTODC11.setQuestion("Total de productos/servicios incorrectos");
        questionsResponseDTODC11.setOrderQuestion(maxOrderQuestion+1);
        questionsResponseDTODC11.setCodeQuestion("DC11");
        for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC11")) {
                questionsResponseDTODC11.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
            }
        }
        questionsResponseDTOS.add(questionsResponseDTODC11);

        List<QuestionsResponseDTO> questionsResponseDTOSorted = questionsResponseDTOS.stream().sorted(Comparator.comparingInt(QuestionsResponseDTO::getOrderQuestion)).toList();

        resultsResponseDTOS.setQuestionsResponseDTOS(questionsResponseDTOSorted);

        return resultsResponseDTOS;
    }

    @Override
    public List<IprDTO> findAllIprByCampaignId(Long campaignId) {
        //ResultsResponseDTO resultsResponseDTO;
        //SearchDTO searchDTO = new SearchDTO();
        Optional<CampaignEntity> campaignEntity = campaignRepository.findById(campaignId);
        List<IprDTO> iprDTOS = iprConverter.convertToModel(iprRepository.findAllByCampaignId(campaignId));
        //List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(campaignId);
        //searchDTO.setCampaignId(campaignId);
        ProtocolEntity protocol;

        for (IprDTO iprDTO : iprDTOS) {
            CampaignEntity entity = campaignEntity.get();
            iprDTO.setNameCampaign(entity.getNameCampaign());
            if (iprDTO.getProtocol().getCode() != null){
                protocol = protocolRepository.findProtocoloByCode(iprDTO.getProtocol().getCode())
                        .orElseThrow(() -> new IllegalStateException("Protocolo no encontrado con el código: " + iprDTO.getProtocol().getCode()));
            } else {
                protocol = protocolRepository.findById(iprDTO.getProtocol().getId())
                        .orElseThrow(() -> new IllegalStateException("Protocolo no encontrado con el ID: " + iprDTO.getProtocol().getId()));
            }

            iprDTO.setProtocolName(protocol.getName());
            if (iprDTO.getCode() == null){
                iprDTO.setIprQuestionDTOList(getAllQuestionsByIprId(iprDTO.getId()));
            }else{
                iprDTO.setIprQuestionDTOList(getAllQuestionsByIprCode(iprDTO.getCode()));
            }
        }
        return iprDTOS;
    }

    public List<IprQuestionDTO> getAllQuestionsByIprId(Long Id){
        List<IprQuestionEntity> iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprId(Id);
        List<IprQuestionDTO> iprQuestionsDTOS = iprQuestionConverter.convertToModel(iprQuestionEntities);

        return iprQuestionsDTOS;
    }

    public List<IprQuestionDTO> getAllQuestionsByIprCode(String code){
        List<IprQuestionEntity> iprQuestionEntities = iprQuestionRepository.findAllQuestionsByIprCode(code);
        List<IprQuestionDTO> iprQuestionsDTOS = iprQuestionConverter.convertToModel(iprQuestionEntities);
        return iprQuestionsDTOS;
    }

    @Override
    public ResultsResponseDTO getResultsIpr(SearchDTO searchDTO) {

        //declaracion de las variables
        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
        List<QuestionsResponseDTO> questionsResponseDTOS = new ArrayList<>();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        List<IprResponseDTO> iprResponseDTOS;
        List<ProtocolResultsResponseDTO> protocolResultsResponseDTOS;
        ProtocolDTO  protocolDTO;

        //obtenemos la entidad campaña
        CampaignEntity campaignEntity = campaignRepository.findById(searchDTO.getCampaignId()).orElseThrow();

        //obtenemos la entidad protocolo y la convertimos en DTO
        //se hace una comprobacion de campo CODE la data antigua maneja un CODE mientras la data nueva maneja ID
        if (searchDTO.getProtocolCode() != null) {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolByCode(searchDTO.getProtocolCode()));
        }else {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolById(searchDTO.getProtocolId()));
        }

        //aqui se crean las respuestas del ipr, cruzando los datos de dos tablas: questions e iprQuestions
        //se hace una comprobacion de campo CODE la data antigua maneja un CODE mientras la data nueva maneja ID
        if (searchDTO.getProtocolCode() != null) {
            iprResponseDTOS = iprRepository.findAllIprByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getIprCode());
            if (iprResponseDTOS.isEmpty()){
                questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolCode(searchDTO.getProtocolCode()));
            }
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getProductServiceCode());
        }else {
            iprResponseDTOS = iprRepository.findAllIprByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId(), searchDTO.getIprId());
            if ( iprResponseDTOS.size() == 0){
                questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolId(searchDTO.getProtocolId()));
            }
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId(), searchDTO.getProductServiceCode());

        }

        //seteamos el nombre de la campaña
        if(campaignEntity.getNameCampaign() != null){
            resultsResponseDTO.setCampaignName(campaignEntity.getNameCampaign());
        }

        //seteamos el nombre del protocolo
        if(protocolDTO != null && protocolDTO.getName() != null){
            resultsResponseDTO.setProtocolName(protocolDTO.getName());
        }

        if (iprResponseDTOS.isEmpty()){
            for (QuestionsDTO questionsDTO : questionsDTOS){
                QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
                String question = questionsDTO.getQuestion();
                String questionText = question != null ? question : "null"; // Si question es null, usa "null", de lo contrario, usa el valor de question
                questionsResponseDTO.setQuestion(questionText);
                questionsResponseDTO.setOrderQuestion(questionsDTO.getOrderQuestion());

                for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                    if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), questionsDTO.getOrderQuestion().toString())) {
                        if (protocolResultsResponseDTO.getCcaaRes() != null) {
                            questionsResponseDTO.addToNumResponseSi(protocolResultsResponseDTO.getCcaaRes());
                        }
                        if (protocolResultsResponseDTO.getCcaaRen() != null) {
                            questionsResponseDTO.addToNumResponseNo(protocolResultsResponseDTO.getCcaaRen());
                        }
                        if (protocolResultsResponseDTO.getCcaaRep() != null) {
                            questionsResponseDTO.addToNumResponseNoProcede(protocolResultsResponseDTO.getCcaaRep());
                        }
                    }
                }
                questionsResponseDTOS.add(questionsResponseDTO);
            }
            resultsResponseDTO.setQuestionsResponseDTOS(questionsResponseDTOS);
            return resultsResponseDTO;

        }else {

            for (IprResponseDTO iprResponseDTO : iprResponseDTOS) {


                QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
                String question = iprResponseDTO.getQuestion();
                String questionText = question != null ? question : "null"; // Si question es null, usa "null", de lo contrario, usa el valor de question
                questionsResponseDTO.setQuestion(questionText);
                questionsResponseDTO.setOrderQuestion(iprResponseDTO.getOrderQuestion());


                if (Objects.equals(iprResponseDTO.getFormula(), "DC0")) {
                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                        if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC0")) {
                            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                            if (iprResponseDTO.getPercentageRespectTo() != null)
                                questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                        }
                    }
                    questionsResponseDTOS.add(questionsResponseDTO);
                    continue;
                }
                if (Objects.equals(iprResponseDTO.getFormula(), "DC8")) {
                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                        if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC8")) {
                            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                            if (iprResponseDTO.getPercentageRespectTo() != null)
                                questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                        }
                    }
                    questionsResponseDTOS.add(questionsResponseDTO);
                    continue;
                }
                if (Objects.equals(iprResponseDTO.getFormula(), "DC9")) {
                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                        if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC9")) {
                            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                            if (iprResponseDTO.getPercentageRespectTo() != null)
                                questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                        }
                    }
                    questionsResponseDTOS.add(questionsResponseDTO);
                    continue;
                }
                if (Objects.equals(iprResponseDTO.getFormula(), "DC10")) {
                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                        if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC10")) {
                            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                            if (iprResponseDTO.getPercentageRespectTo() != null)
                                questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                        }
                    }
                    questionsResponseDTOS.add(questionsResponseDTO);
                    continue;

                }
                if (Objects.equals(iprResponseDTO.getFormula(), "DC11")) {
                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                        if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC11")) {
                            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                            if (iprResponseDTO.getPercentageRespectTo() != null)
                                questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                        }
                    }
                    questionsResponseDTOS.add(questionsResponseDTO);
                    continue;
                }

                if (iprResponseDTO.getFormula() == null) {
                    questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());
                    questionsResponseDTOS.add(questionsResponseDTO);

                    continue;
                } else {

                    for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {

                        if (iprResponseDTO.getPercentageRespectTo() != null)
                            questionsResponseDTO.setPercentageRespectTo(iprResponseDTO.getPercentageRespectTo());

                        if (iprResponseDTO.getFormula() != null && !protocolResultsResponseDTO.getCodeQuestion().startsWith("DC")) {
                            List<String> componentes = descomponerFormula(iprResponseDTO.getFormula());
                            interpretarYProcesarComponentes(componentes, protocolResultsResponseDTO, questionsResponseDTO);
                        } else {
                            continue;
                        }

                    }
                }


                questionsResponseDTOS.add(questionsResponseDTO);
            }
        }

        setPercentage(questionsResponseDTOS);

        resultsResponseDTO.setQuestionsResponseDTOS(questionsResponseDTOS);

        return resultsResponseDTO;
    }

    private List<String> descomponerFormula(String formula) {
        List<String> componentes = new ArrayList<>();
        // La expresión regular incluye operadores de suma y resta
        Pattern pattern = Pattern.compile("([NSNP]+\\d+)|([+-])");
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            componentes.add(matcher.group());
        }

        return componentes;
    }

    private void interpretarYProcesarComponentes(List<String> componentes, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        for (String componente : componentes) {
            if (componentes.size() == 1 && !componentes.get(0).matches("[+-]")) {
                // Solo hay un componente y no es un operador matemático
                procesarComponenteIndividual(componentes.get(0), protocolResultsResponseDTO, questionsResponseDTO);
            }else {

                String tipo = componente.replaceAll("\\d+", "");

                if (tipo.equals("+") || tipo.equals("-")) {
                    continue;
                }else {
                    int orden = Integer.parseInt(componente.replaceAll("\\D+", ""));

                    switch (tipo) {
                        case "N":
                            procesarTipoN(orden, protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                        case "S":
                            procesarTipoS(orden,  protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                        case "NP":
                            procesarTipoNP(orden, protocolResultsResponseDTO, questionsResponseDTO);
                            break;
                    }
                }
            }
        }
    }

    private void procesarComponenteIndividual(String componente, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        String tipo = componente.replaceAll("\\d+", "");
        int orden = Integer.parseInt(componente.replaceAll("\\D+", ""));

        switch (tipo) {
            case "N":
                procesarTipoN(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
            case "S":
                procesarTipoS(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
            case "NP":
                procesarTipoNP(orden, protocolResultsResponseDTO, questionsResponseDTO);
                break;
        }


    }

    private void procesarTipoN(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRen());
        }
    }

    private void procesarTipoS(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
        }
    }

    private void procesarTipoNP(int orden, ProtocolResultsResponseDTO protocolResultsResponseDTO, QuestionsResponseDTO questionsResponseDTO) {
        if (orden == Integer.parseInt(protocolResultsResponseDTO.getCodeQuestion())){
            questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRep());
        }
    }

    private void setPercentage(List<QuestionsResponseDTO> questionsResponseDTOS) {

        for (QuestionsResponseDTO questionsResponseDTO : questionsResponseDTOS) {
            if (questionsResponseDTO.getPercentageRespectTo() != null) {

                for (QuestionsResponseDTO item : questionsResponseDTOS) {
                    if (questionsResponseDTO.getPercentageRespectTo().equals(item.getOrderQuestion()) && item.getTotal() != 0) {
                        Float value = questionsResponseDTO.getTotal().floatValue() / item.getTotal() * 100;
                        BigDecimal bd = new BigDecimal(value);
                        bd = bd.setScale(2, RoundingMode.HALF_UP); // Redondeo a 2 decimales
                        questionsResponseDTO.setPercentage(bd.floatValue());
                    }
                }
            }
        }

    }

}
