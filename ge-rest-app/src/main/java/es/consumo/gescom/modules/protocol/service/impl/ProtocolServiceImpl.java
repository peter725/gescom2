package es.consumo.gescom.modules.protocol.service.impl;

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
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.protocol.model.converter.ProtocolConverter;
import es.consumo.gescom.modules.protocol.model.criteria.ProtocolCriteria;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDetailDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.questions.model.converter.QuestionsConverter;
import es.consumo.gescom.modules.questions.model.dto.QuestionDetailDTO;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.model.entity.QuestionsEntity;
import es.consumo.gescom.modules.questions.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Page<ProtocolEntity> getProtocolByNameOrCode(CriteriaWrapper<ProtocolCriteria> wrapper, String protocol, String code) {
        return ((ProtocolRepository) repository).getProtocolByNameOrCode(wrapper.getCriteria().toPageable(), protocol, code);

    }

    @Override
    public List<ProtocolDTO> findProtocolByCampaignId(Long idCampaign) {

        List<ProtocolEntity> protocolEntity = protocolRepository.findProtocolByCampaignId(idCampaign);
        List<ProtocolDTO> listProtocolDTO = protocolConverter.convertToModel(protocolEntity);
        List<CampaignProductServiceEntity> campaignProductServiceEntities = campaignProductServiceRepository.findCampaignProductServiceByCampaignId(idCampaign);


        for (ProtocolDTO protocolDTO : listProtocolDTO) {
            if (protocolDTO.getCode() == null) {
                SearchDTO searchDTO = new SearchDTO();
                searchDTO.setCampaignId(idCampaign);
                searchDTO.setProtocolId(protocolDTO.getId());
                searchDTO.setProductServiceCode(campaignProductServiceEntities.get(0).getCodeProductService());
                List<IprDTO> iprDTOS = iprService.findAllIprByCampaignIdAndProtocolId(idCampaign, protocolDTO.getId());
                List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolId(protocolDTO.getId());
                List<QuestionsDTO> listQuestionsDTOS = questionsConverter.convertToModel(questionsEntities);

                ResultsResponseDTO resultsResponseDTO = iprService.getResults(searchDTO);
                protocolDTO.setQuestion(listQuestionsDTOS);
                protocolDTO.setResultsResponseDTO(resultsResponseDTO);
                protocolDTO.setIprDTOS(iprDTOS);

            }else {
                List<IprDTO> iprDTOS = iprService.findAllIprByCampaignIdAndProtocolCode(idCampaign, protocolDTO.getCode());
                List<QuestionsEntity> questionsEntities = questionsRepository.findAllQuestionsByProtocolCode(protocolDTO.getCode());
                List<QuestionsDTO> listQuestionsDTOS = questionsConverter.convertToModel(questionsEntities);
                protocolDTO.setQuestion(listQuestionsDTOS);
                protocolDTO.setIprDTOS(iprDTOS);
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
    public ProtocolDetailDTO findProtocolById(Long id) {
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
        return protocolConverter.convertToModel(protocolRepository.findProtocolNameById(id));
    }

    @Override
    public ProtocolDTO findProtocolByCode(String code) {
        return protocolConverter.convertToModel(protocolRepository.findProtocolNameByCode(code));
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


}
