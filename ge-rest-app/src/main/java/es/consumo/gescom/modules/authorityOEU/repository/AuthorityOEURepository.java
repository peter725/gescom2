package es.consumo.gescom.modules.authorityOEU.repository;

import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.ambit.model.criteria.AmbitCriteria;
import es.consumo.gescom.modules.ambit.model.entity.AmbitEntity;
import es.consumo.gescom.modules.authorityOEU.model.criteria.AuthorityOEUCriteria;
import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface AuthorityOEURepository extends GESCOMRepository<AuthorityOEUEntity, Long> , QueryByCriteria<AuthorityOEUEntity.SimpleProjection, AuthorityOEUCriteria> {

    @Query(value = "SELECT h FROM AuthorityOEUEntity h  where h.name = :name ")
    Page<AuthorityOEUEntity.SimpleProjection> findAllAuthorityOEUByName( Pageable pageable, @Param("name") String name);

}
