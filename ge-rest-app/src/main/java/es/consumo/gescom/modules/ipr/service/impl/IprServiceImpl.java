package es.consumo.gescom.modules.ipr.service.impl;

import es.consumo.gescom.modules.campaign.model.converter.CampaignConverter;
import es.consumo.gescom.modules.campaign.model.dto.QuestionsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.ResultsResponseDTO;
import es.consumo.gescom.modules.campaign.model.dto.SearchDTO;
import es.consumo.gescom.modules.campaign.model.entity.CampaignEntity;
import es.consumo.gescom.modules.campaign.repository.CampaignRepository;
import es.consumo.gescom.modules.campaign.service.CampaignService;
import es.consumo.gescom.modules.campaignProductService.model.dto.CampaignProductServiceDTO;
import es.consumo.gescom.modules.campaignProductService.model.entity.CampaignProductServiceEntity;
import es.consumo.gescom.modules.campaignProductService.repository.CampaignProductServiceRepository;
import es.consumo.gescom.modules.ipr.model.converter.IprConverter;
import es.consumo.gescom.modules.ipr.model.criteria.IprCriteria;
import es.consumo.gescom.modules.ipr.model.dto.IprDTO;
import es.consumo.gescom.modules.ipr.model.dto.IprResponseDTO;
import es.consumo.gescom.modules.ipr.model.entity.IprEntity;
import es.consumo.gescom.modules.ipr.repository.IprRepository;
import es.consumo.gescom.modules.ipr.service.IprService;
import es.consumo.gescom.modules.iprQuestion.model.dto.IprQuestionDTO;
import es.consumo.gescom.modules.iprQuestion.model.entity.IprQuestionEntity;
import es.consumo.gescom.modules.iprQuestion.repository.IprQuestionRepository;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import es.consumo.gescom.modules.productServices.repository.ProductServiceRepository;
import es.consumo.gescom.modules.protocol.model.converter.ProtocolConverter;
import es.consumo.gescom.modules.protocol.model.dto.ProtocolDTO;
import es.consumo.gescom.modules.protocol.model.entity.ProtocolEntity;
import es.consumo.gescom.modules.protocol.repository.ProtocolRepository;
import es.consumo.gescom.modules.protocol.service.ProtocolService;
import es.consumo.gescom.modules.protocol_results.model.dto.ProtocolResultsResponseDTO;
import es.consumo.gescom.modules.protocol_results.repository.ProtocolResultsRepository;
import es.consumo.gescom.modules.questions.model.converter.QuestionsConverter;
import es.consumo.gescom.modules.questions.model.dto.QuestionsDTO;
import es.consumo.gescom.modules.questions.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.EntityCrudService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private QuestionsConverter questionsConverter;


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
    public List<IprDTO> findAllIprByCampaignIdAndProtocolCode(Long campaignId, String protocolCode) {
        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
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
    public List<ResultsResponseDTO> getResultProtocol(SearchDTO searchDTO) {
        List<ResultsResponseDTO> resultsResponseDTOS = new ArrayList<>();
        List<QuestionsResponseDTO> questionsResponseDTOS = new ArrayList<>();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        List<IprResponseDTO> iprResponseDTOS = new ArrayList<>();
        List<ProtocolResultsResponseDTO> protocolResultsResponseDTOS = new ArrayList<>();
        ProductServiceEntity productServiceEntity = new ProductServiceEntity();
        ProtocolDTO  protocolDTO = new ProtocolDTO();

        CampaignEntity campaignEntity = campaignRepository.findById(searchDTO.getCampaignId()).orElseThrow();
        if (searchDTO.getProtocolCode() != null) {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolNameByCode(searchDTO.getProtocolCode()));
        }else {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolNameById(searchDTO.getProtocolId()));
        }

        if (searchDTO.getProtocolCode() != null) {
            questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolCode(searchDTO.getProtocolCode()));
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getProductServiceCode());
        }else {
            questionsDTOS = questionsConverter.convertToModel(questionsRepository.findAllQuestionsByProtocolId(searchDTO.getProtocolId()));
            protocolResultsResponseDTOS = protocolResultsRepository.findProtocolResultsByCampaignIdAndProtocolId(searchDTO.getCampaignId(), searchDTO.getProtocolId(), searchDTO.getProductServiceCode());

        }

        /*for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
            QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
            if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC1")) {
                questionsResponseDTO.setQuestion("DC1- Nro. de establecimientos existentes");
                questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                questionsResponseDTOS.add(questionsResponseDTO);
            }
        }*/

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

        return resultsResponseDTOS;
    }



    @Override
    public ResultsResponseDTO getResultsIpr(SearchDTO searchDTO) {
        ResultsResponseDTO resultsResponseDTO = new ResultsResponseDTO();
        List<QuestionsResponseDTO> questionsResponseDTOS = new ArrayList<>();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        List<IprResponseDTO> iprResponseDTOS = new ArrayList<>();
        List<ProtocolResultsResponseDTO> protocolResultsResponseDTOS = new ArrayList<>();
        ProductServiceEntity productServiceEntity = new ProductServiceEntity();
        ProtocolDTO  protocolDTO = new ProtocolDTO();

        CampaignEntity campaignEntity = campaignRepository.findById(searchDTO.getCampaignId()).orElseThrow();
        if (searchDTO.getProtocolCode() != null) {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolNameByCode(searchDTO.getProtocolCode()));
        }else {
            protocolDTO = protocolConverter.convertToModel(protocolRepository.findProtocolNameById(searchDTO.getProtocolId()));
        }

        if (searchDTO.getProtocolCode() != null) {
            iprResponseDTOS = iprRepository.findAllIprByCampaignIdAndProtocolCode(searchDTO.getCampaignId(), searchDTO.getProtocolCode(), searchDTO.getIprCode());
            if ( iprResponseDTOS.size() == 0){
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



        /*if (searchDTO.getProductServiceCode() != null) {
            productServiceEntity = productServiceRepository.findProductServiceByCode(searchDTO.getProductServiceCode());
        }else {
            productServiceEntity = productServiceRepository.findProductServiceById(searchDTO.getProductServiceId());
        }*/

        resultsResponseDTO.setCampaignName(campaignEntity.getNameCampaign());
        resultsResponseDTO.setProtocolName(protocolDTO.getName());
       //resultsResponseDTO.setProductName(productServiceEntity.getCode().concat(" - ").concat(productServiceEntity.getName()));
        if (iprResponseDTOS.size() == 0){


            /*for (ProtocolResultsResponseDTO protocolResultsResponseDTO : protocolResultsResponseDTOS) {
                QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
                if (Objects.equals(protocolResultsResponseDTO.getCodeQuestion(), "DC1")) {
                    questionsResponseDTO.setQuestion("DC1- Nro. de establecimientos existentes");
                    questionsResponseDTO.addToTotal(protocolResultsResponseDTO.getCcaaRes());
                    questionsResponseDTOS.add(questionsResponseDTO);
                }
            }*/

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
