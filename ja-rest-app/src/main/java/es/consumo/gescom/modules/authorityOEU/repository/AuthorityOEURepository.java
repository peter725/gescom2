package es.consumo.gescom.modules.authorityOEU.repository;

import es.consumo.gescom.modules.authorityOEU.model.entity.AuthorityOEUEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;

@Repository
public interface AuthorityOEURepository extends GESCOMRepository<AuthorityOEUEntity, Long> {

    @Query(value = "SELECT h FROM AuthorityOEUEntity h  where h.name = :name ")
    Page<AuthorityOEUEntity.SimpleProjection> findAllAuthorityOEUByName(
    Pageable pageable,
    @Param("name") String name);

}
