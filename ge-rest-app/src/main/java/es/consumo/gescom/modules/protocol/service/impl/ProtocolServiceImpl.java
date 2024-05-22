package es.consumo.gescom.modules.protocol.service.impl;

import es.consumo.gescom.commons.dto.FilterCriteria;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.modules.autonomousCommunityParticipants.model.entity.AutonomousCommunityParticipantsEntity;
import es.consumo.gescom.modules.autonomousCommunityParticipants.repository.AutonomousCommunityParticipantsRepository;
import es.consumo.gescom.modules.campaign.model.converter.CampaignConverter;
import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.SearchDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.repository.CampaignProductServiceRepository;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.protocol.model.converter.ProtocolConverter;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDetailDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.protocol_results.model.entity.ProtocolResultsEntity;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.questions.model.converter.QuestionsConverter;
import es.consumo.gescom.modules.questions.model.dto.QuestionDetailDTO;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import es.consumo.gescom.modules.questions.repository.QuestionsRepository;
import es.consumo.gescom.modules.totalProtocolResults.model.entity.TotalProtocolResultsEntity;
import es.consumo.gescom.modules.totalProtocolResults.repository.TotalProtocolResultsRepository;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProtocolServiceImpl extends EntityCrudService<ProtocolEntity, Long> implements ProtocolService {

    @Autowired
    private ProtocolConverter protocolConverter;

    @Autowired
    private CampaignConverter campaignConverter;

    @Autowired
    private QuestionsConverter questionsConverter;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private IprRepository iprRepository;

    @Autowired
    private AutonomousCommunityParticipantsRepository autonomousCommunityParticipantsRepository;


    protected ProtocolServiceImpl(GESCOMRepository<ProtocolEntity, Long> repository) {
        super(repository);
    }

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private CampaignProductServiceRepository campaignProductServiceRepository;

    @Autowired
    private IprService iprService;
    
    @Autowired
    private ProtocolResultsRepository protocolResultsRepository;
    
    @Autowired 
    private TotalProtocolResultsRepository totalProtocolResultsRepository;

    @Override
    public Page<ProtocolEntity> getProtocolByNameOrCode(CriteriaWrapper<ProtocolCriteria> wrapper, String protocol, String code) {
        return ((ProtocolRepository) repository).getProtocolByNameOrCode(wrapper.getCriteria().toPageable(), protocol, code);

    }
    
    @Transactional
    @Override
    public void deleteById(Long id) {
    	// Buscamos Protocolo
    	ProtocolEntity protocol = protocolRepository.findById(id).orElseThrow();
    	// Bucamos Preguntas
    	List<QuestionsEntity> questions = questionsRepository.findAllQuestionsByProtocolId(id);
    	
    	// Buscamos posibles resultados de Protocolo (puede haber uno por cada CCAA participante)
    	List<ProtocolResultsEntity> protocolResults = protocolResultsRepository.findAllByCampaignIdAndProtocolCode(protocol.getCampaignId().getId(), protocol.getCode());
    	protocolResults.forEach(result -> {
    		// Por cada resultado de Protocolo buscamos y borramos los resultados de sus Preguntas
    		List<TotalProtocolResultsEntity> totalProtocolResults = new ArrayList<TotalProtocolResultsEntity>();
    		if (result.getCode() != null) {
        		totalProtocolResults = totalProtocolResultsRepository.findAllByProtocolResultsCode(result.getCode());
    		} else {
    			totalProtocolResults = totalProtocolResultsRepository.findAllByProtocolResultsId(result.getId());
    		}
    		totalProtocolResultsRepository.deleteAll(totalProtocolResults);
    		
    	});
    	// Borramos los resultados del Protocolo cuyos resultados Preguntas ya han sido borradas.
    	protocolResultsRepository.deleteAll(protocolResults);
    	
    	// Borramos las Preguntas del Protocolo y el mismo Protocolo.
    	questionsRepository.deleteAll(questions);
    	protocolRepository.delete(protocol);
    }

    @Override
    public List<ProtocolDTO> findProtocolByCampaignId(Long idCampaign) {

        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
        SearchDTO searchDTO = new SearchDTO();
        List<ProtocolEntity> protocolEntity = protocolRepository.findProtocolByCampaignId(idCampaign);
        List<ProtocolDTO> listProtocolDTO = protocolConverter.convertToModel(protocolEntity);
        List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(idCampaign);
        Set<CampaignProductServiceEntity> campaignProductServiceEntitySet = new HashSet<>(campaignProductServiceEntities);

        for (ProtocolDTO protocolDTO : listProtocolDTO) {

            if (protocolDTO.getCode() == null) {
                searchDTO.setCampaignId(idCampaign);
                searchDTO.setProtocolId(protocolDTO.getId());
                if (campaignProductServiceEntitySet.size() > 0) {

                    for (CampaignProductServiceEntity campaignProductServiceEntity : campaignProductServiceEntitySet) {
                        searchDTO.setProductServiceCode(campaignProductServiceEntity.getCodeProductService());

                        searchDTO.setProductServiceCode(campaignProductServiceEntities.get(0).getCodeProductService());
                        List<IprDTO> iprDTOS = iprService.findAllIprByCampaignIdAndProtocolId(idCampaign, protocolDTO.getId());
                        resultsResponseDTO = iprService.getResultProtocol(searchDTO);
                        protocolDTO.setResultsResponseDTO(resultsResponseDTO);
                        protocolDTO.setIprDTOS(iprDTOS);
                    }
                }
                List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolId(protocolDTO.getId());
                List<QuestionsDTO> listQuestionsDTOS = questionsConverter.convertToModel(questionsEntities);
                protocolDTO.setQuestion(listQuestionsDTOS);


            }else {
                searchDTO.setCampaignId(idCampaign);
                searchDTO.setProtocolCode(protocolDTO.getCode());
                if (campaignProductServiceEntitySet.size() > 0) {

                    for (CampaignProductServiceEntity campaignProductServiceEntity : campaignProductServiceEntitySet) {
                        searchDTO.setProductServiceCode(campaignProductServiceEntity.getCodeProductService());
                        List<IprDTO> iprDTOS = iprService.findAllIprByCampaignIdAndProtocolCode(idCampaign, protocolDTO.getCode());
                        resultsResponseDTO = iprService.getResultProtocol(searchDTO);
                        protocolDTO.setIprDTOS(iprDTOS);
                        protocolDTO.setResultsResponseDTO(resultsResponseDTO);


                    }
                }
                List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolCode(protocolDTO.getCode());
                List<QuestionsDTO> listQuestionsDTOS = questionsConverter.convertToModel(questionsEntities);
                protocolDTO.setQuestion(listQuestionsDTOS);

            }
        }
        return (listProtocolDTO);
    }

    @Override
    public ProtocolDTO createProtocol(ProtocolDTO payload) {

        ProtocolEntity protocol = protocolConverter.convertToEntity(payload);
        protocol.setCode(payload.getCode());
        protocol.setName(payload.getName());
        CampaignEntity campaignEntity =  campaignRepository.findById(payload.getCampaignId()).orElseThrow();
        protocol.setCampaignId(campaignEntity);
        protocol.setCreatedAt(LocalDateTime.now());
        protocol.setUpdatedAt(LocalDateTime.now());
        ProtocolEntity protocolSave = protocolRepository.save(protocol);

        List<QuestionsDTO> questions = payload.getQuestion();
        questions.forEach(question -> {
            QuestionsEntity questionsEntity = new QuestionsEntity();
            questionsEntity.setCode(question.getCode());
            questionsEntity.setProtocolCampaingId(protocolSave);
            questionsEntity.setQuestion(question.getQuestion());
            questionsEntity.setCodeInfringement(question.getCodeInfringement());
            questionsEntity.setOrderQuestion(question.getOrderQuestion());
            questionsEntity.setCodeQuestion(question.getCodeQuestion());
            questionsEntity.setBkTrinti(question.getBkTrinti());
            if(question.getResponse().equals("SI")){
                questionsEntity.setResponse("S");
            }else if (question.getResponse().equals("NO")){
                questionsEntity.setResponse("N");
            }
            questionsEntity.setBkTrrees(question.getBkTrrees());
            questionsEntity.setProtocolCampaignCode(question.getProtocolCampaignCode());
            questionsEntity.setCreatedAt(LocalDateTime.now());
            questionsEntity.setUpdatedAt(LocalDateTime.now());

            questionsRepository.save(questionsEntity);
        });

        return protocolConverter.convertToModel(protocolSave);
    }

    @Override
    public ProtocolDetailDTO findProtocolDetailById(Long id) {
        Optional<ProtocolEntity> protocol = protocolRepository.findById(id);
        CampaignEntity campaignEntity = protocol.get().getCampaignId();
        List<QuestionDetailDTO> questionDetailDTOList = new ArrayList<>();
        List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolId(id);
        questionsEntities.forEach(questionsEntity -> {
            QuestionDetailDTO questionDetailDTO = new QuestionDetailDTO();
            questionDetailDTO.setQuestion(questionsEntity.getQuestion());
            questionDetailDTO.setResponse(questionsEntity.getResponse());
            questionDetailDTO.setInfringement(questionsEntity.getCodeInfringement());
            questionDetailDTOList.add(questionDetailDTO);
        } );
        List<String> ccaaParticipants = new ArrayList<>();
        List<AutonomousCommunityParticipantsEntity> autonomousCommunityParticipants = autonomousCommunityParticipantsRepository.findByIdCampaign(campaignEntity.getId());
        autonomousCommunityParticipants.forEach(autonomousCommunityParticipantsEntity -> {
            ccaaParticipants.add(autonomousCommunityParticipantsEntity.getAutonomousCommunityEntity().getName());
        });
        String participants = String.join(" ", ccaaParticipants);
        return getProtocolDetailDTO(questionDetailDTOList, protocol, campaignEntity, participants);
    }

    @Override
    public List<QuestionsDTO> findProtocolByIdOrCode(ProtocolDTO protocolDTO) {

        List<QuestionsDTO> questionDetailDTOList = new ArrayList<>();
        if (protocolDTO.getCode().equals("undefined")){
            protocolDTO.setCode(null);
        }

        if(protocolDTO.getCode() == null){
            List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolId(protocolDTO.getId());
            questionsEntities.forEach( questionsEntity -> {
                QuestionsDTO questionsDTO = new QuestionsDTO();
                questionsDTO.setQuestion(questionsEntity.getQuestion());
                questionsDTO.setCodeQuestion(questionsEntity.getCodeQuestion());
                questionDetailDTOList.add(questionsDTO);
            });
        }else {
            List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolCode(protocolDTO.getCode());
            questionsEntities.forEach( questionsEntity -> {
                QuestionsDTO questionsDTO = new QuestionsDTO();
                questionsDTO.setQuestion(questionsEntity.getQuestion());
                questionsDTO.setCodeQuestion(questionsEntity.getCodeQuestion());
                questionDetailDTOList.add(questionsDTO);
            });
        }
        return questionDetailDTOList;
    }

    @Override
    public ProtocolDTO findProtocolDTOById(Long id) {
        ProtocolEntity protocolEntity = protocolRepository.findById(id).orElseThrow();
        ProtocolDTO protocolDTO = protocolConverter.convertToModel(protocolEntity);
        List<QuestionsEntity> questionsEntities = new ArrayList<>();
        if (protocolDTO.getCode() == null){
            questionsEntities = questionsRepository.findAllQuestionsByProtocolId(protocolDTO.getId());
        }else{
            questionsEntities = questionsRepository.findAllQuestionsByProtocolCode(protocolDTO.getCode());
        }
        List<QuestionsDTO> questionsDTOS = questionsConverter.convertToModel(questionsEntities);
        protocolDTO.setQuestion(questionsDTOS);


        return protocolDTO;
    }

    @Override
    public ProtocolDTO findProtocolByCode(String code) {
        return protocolConverter.convertToModel(protocolRepository.findProtocolNameByCode(code));
    }

    @Override
    public ProtocolDTO updateProtocol(Long id, ProtocolDTO payload) {
        ProtocolEntity protocol = protocolConverter.convertToEntity(payload);

        final List<Long> toDelete = new ArrayList<>();

        if (ObjectUtils.isEmpty(protocol.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (protocol.getId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        protocol.setId(payload.getId());
        protocol.setCode(payload.getCode());
        protocol.setName(payload.getName());

        CampaignEntity campaignEntity =  campaignRepository.findById(payload.getCampaignId()).orElseThrow();
        protocol.setCampaignId(campaignEntity);
        protocol.setCreatedAt(LocalDateTime.now());
        protocol.setUpdatedAt(LocalDateTime.now());
        ProtocolEntity protocolSave = protocolRepository.save(protocol);

        List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolId(protocolSave.getId());
        iterateAndDeletedQuestions(questionsEntities, toDelete);

        if (!toDelete.isEmpty())
            questionsRepository.deleteAllById(toDelete);

        List<QuestionsDTO> questions = payload.getQuestion();
        questions.forEach(question -> {
            QuestionsEntity questionsEntity = new QuestionsEntity();
            questionsEntity.setCode(question.getCode());
            questionsEntity.setProtocolCampaingId(protocolSave);
            questionsEntity.setQuestion(question.getQuestion());
            questionsEntity.setCodeInfringement(question.getCodeInfringement());
            questionsEntity.setOrderQuestion(question.getOrderQuestion());
            questionsEntity.setCodeQuestion(question.getCodeQuestion());
            questionsEntity.setBkTrinti(question.getBkTrinti());
            if(question.getResponse().equals("SI")){
                questionsEntity.setResponse("S");
            }else if (question.getResponse().equals("NO")){
                questionsEntity.setResponse("N");
            }
            questionsEntity.setBkTrrees(question.getBkTrrees());
            questionsEntity.setProtocolCampaignCode(question.getProtocolCampaignCode());
            questionsEntity.setCreatedAt(LocalDateTime.now());
            questionsEntity.setUpdatedAt(LocalDateTime.now());

            questionsRepository.save(questionsEntity);
        });

        return protocolConverter.convertToModel(protocolSave);
    }

    private void iterateAndDeletedQuestions(List<QuestionsEntity> questionsEntities, List<Long> toDelete) {

        for(QuestionsEntity questionsEntity : questionsEntities){
            if(questionsEntity.getId() != (0)){
                if(Objects.nonNull(toDelete)){
                    toDelete.add(questionsEntity.getId());
                }
            }
        }
    }

    private static ProtocolDetailDTO getProtocolDetailDTO(List<QuestionDetailDTO> questionDetailDTOList, Optional<ProtocolEntity> protocol, CampaignEntity campaignEntity, String participants) {
        ProtocolDetailDTO result = new ProtocolDetailDTO();
        result.setQuestions(questionDetailDTOList);
        result.setProtocolName(protocol.get().getName());
        result.setParticipants(participants);
        result.setAmbit(campaignEntity.getAmbit().getName());
        String yearSting = campaignEntity.getYear().toString();
        result.setYear(yearSting);
        result.setCampaignName(campaignEntity.getNameCampaign());
        result.setCodeCPA(campaignEntity.getCodeCpa());
        result.setTypeCampaign(campaignEntity.getCampaignType().getName());
        result.setResponsible(campaignEntity.getAutonomousCommunityResponsible().getName());

        return result;
    }

    @Override
    protected Page<ProtocolEntity.SimpleProjection> findAllFromCriteria(FilterCriteria criteria) {

        ProtocolCriteria protocolCriteria = (ProtocolCriteria) criteria;
        if (protocolCriteria.getSearch() != null) {
            protocolCriteria.setSearch(protocolCriteria.getSearch().toUpperCase());
        }
        protocolCriteria.setSort(new String[]{"id;asc"});
        Page<ProtocolEntity.SimpleProjection> protocolSimpleProjections = ((ProtocolRepository) repository).findAllByCriteria(protocolCriteria, protocolCriteria.toPageable());

        return protocolSimpleProjections;

    }
}
