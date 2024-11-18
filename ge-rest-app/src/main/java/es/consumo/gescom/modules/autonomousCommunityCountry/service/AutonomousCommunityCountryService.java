package es.consumo.gescom.modules.autonomousCommunityCountry.service;

import es.consumo.gescom.modules.autonomousCommunity.model.criteria.AutonomousCommunityCriteria;
import es.consumo.gescom.modules.autonomousCommunity.model.entity.AutonomousCommunityEntity;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.criteria.AutonomousCommunityCountryCriteria;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.dto.AutonomousCommunityCountryDTO;
import es.consumo.gescom.modules.autonomousCommunityCountry.model.entity.AutonomousCommunityCountryEntity;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import org.springframework.data.domain.Page;

import es.consumo.gescom.commons.dto.wrapper.CriteriaWrapper;
import es.consumo.gescom.commons.service.CrudService;

public interface AutonomousCommunityCountryService extends CrudService<AutonomousCommunityCountryEntity, Long>{

    Page<AutonomousCommunityCountryEntity.SimpleProjection> findAllAutonomousCommunityCountryByName(CriteriaWrapper<AutonomousCommunityCountryCriteria> wrapper, Long id);

    AutonomousCommunityCountryDTO findByCode(String code);

    AutonomousCommunityCountryDTO findCCAAById(Long id);

}
