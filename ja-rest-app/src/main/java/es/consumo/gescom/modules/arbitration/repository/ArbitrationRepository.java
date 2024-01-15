package es.consumo.gescom.modules.arbitration.repository;

import es.consumo.gescom.commons.db.repository.JJAARepository;
import es.consumo.gescom.commons.db.repository.QueryByCriteria;
import es.consumo.gescom.modules.arbitration.model.criteria.ArbitrationCriteria;
import es.consumo.gescom.modules.arbitration.model.entity.ArbitrationEntity;
import es.consumo.gescom.modules.arbitrationBoard.model.entity.ArbitrationBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitrationRepository extends JJAARepository<ArbitrationEntity, Long>,
        QueryByCriteria<ArbitrationEntity.SimpleProjection, ArbitrationCriteria> {


    @Query(value = "SELECT a.id as id,a.createAt as createAt,ab.name as boardName,a.claimant.name as claimantName, " +
            "a.claimant.lastname as claimantLastname,a.claimant.dni as claimantDni, " +
            "nt.name as notificationType,c.nif as claimedNif,c.socialReason as claimedSocialReason, a.status as status " +
            "FROM ArbitrationEntity a "
            + "LEFT JOIN a.claimed c "
            + "LEFT JOIN a.notificationType nt "
            + "LEFT JOIN a.arbitrationBoard ab "
            + "LEFT JOIN a.status s  "
            + "WHERE "
            + "(:#{#criteria.claimedNif} is null OR c.nif like :#{#criteria.claimedNif}) "
            + "AND (:#{#criteria.claimedSocialReason} is null OR c.socialReason like :#{#criteria.claimedSocialReason}) "
            + "AND (:#{#criteria.status} is null OR s.name like :#{#criteria.status}) "
            + "AND (:#{#criteria.arbitrationBoard} is null OR ab.name like :#{#criteria.arbitrationBoard}) "
            + "AND (:#{#criteria.tipoNoTif} is null OR nt.name like :#{#criteria.tipoNoTif}) "
            + "AND (:#{#criteria.search} is null OR nt.name like  :#{#criteria.search} " +
            "OR c.socialReason like  :#{#criteria.search} " +
            "OR c.nif like  :#{#criteria.search} " +
            "OR nt.name like  :#{#criteria.search}) " +
            "OR s.name like  :#{#criteria.search} " +
            "OR ab.name like  :#{#criteria.search} " +
            "OR nt.name like  :#{#criteria.search} "

    )
    Page<ArbitrationEntity.SimpleProjection> findAllByCriteria(ArbitrationCriteria criteria, Pageable pageable);

    @Query(value = "SELECT a.id as id,a.createAt as createAt,ab.name as boardName,a.claimant.name as claimantName, " +
            "a.claimant.lastname as claimantLastname,a.claimant.dni as claimantDni, " +
            "nt.name as notificationType,c.nif as claimedNif,c.socialReason as claimedSocialReason, a.status as status " +
            "FROM ArbitrationEntity a "
            + "LEFT JOIN a.claimed c "
            + "LEFT JOIN a.notificationType nt "
            + "LEFT JOIN a.arbitrationBoard ab "
            + "LEFT JOIN a.status s  "
            + "WHERE "
            + " a.arbitrationBoard is null OR a.arbitrationBoard = :arbitrationBoard "
            + "AND (:dni is null AND s.id != 1 OR c.nif = :dni)"
            + "AND (:#{#criteria.claimedNif} is null OR c.nif like :#{#criteria.claimedNif}) "
            + "AND (:#{#criteria.claimedSocialReason} is null OR c.socialReason like :#{#criteria.claimedSocialReason}) "
            + "AND (:#{#criteria.status} is null OR s.name like :#{#criteria.status}) "
            + "AND (:#{#criteria.arbitrationBoard} is null OR ab.name like :#{#criteria.arbitrationBoard}) "
            + "AND (:#{#criteria.tipoNoTif} is null OR nt.name like :#{#criteria.tipoNoTif}) "
            + "AND (:#{#criteria.search} is null OR nt.name like  :#{#criteria.search} " +
            "OR c.socialReason like  :#{#criteria.search} " +
            "OR c.nif like  :#{#criteria.search} " +
            "OR nt.name like  :#{#criteria.search}) " +
            "OR s.name like  :#{#criteria.search} " +
            "OR ab.name like  :#{#criteria.search} " +
            "OR nt.name like  :#{#criteria.search} "
    )
    Page<ArbitrationEntity.SimpleProjection> findAllByCriteriaAndArbitrationBoard(ArbitrationCriteria criteria, 
    Pageable pageable, 
    @Param("arbitrationBoard") ArbitrationBoardEntity arbitrationBoard,
    @Param("dni") String dni);
}
