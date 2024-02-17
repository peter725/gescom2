package es.consumo.gescom.modules.productServices.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServiceRepository extends GESCOMRepository<ProductServiceEntity, Long> {

    @Query(value = "SELECT h FROM CampaignProductServiceEntity h where h.id = :id ")
    Page<ProductServiceEntity.SimpleProjection> findAllProductServiceById(Pageable pageable, Long id);
}
