package es.consumo.gescom.modules.productServices.repository;

import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.productServices.model.dto.ProductServiceDTO;
import es.consumo.gescom.modules.productServices.model.entity.ProductServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductServiceRepository extends GESCOMRepository<ProductServiceEntity, Long> {

    @Query(value = "SELECT h FROM CampaignProductServiceEntity h where h.id = :id ")
    Page<ProductServiceEntity.SimpleProjection> findAllProductServiceById(Pageable pageable, Long id);

    @Query(value = "SELECT h FROM ProductServiceEntity h where h.code = :code ")
    ProductServiceEntity findProductServiceByCode(String code);


    @Query(value = "SELECT h FROM ProductServiceEntity h where h.id = :id ")
    ProductServiceEntity findProductServiceById(Long id);
}
