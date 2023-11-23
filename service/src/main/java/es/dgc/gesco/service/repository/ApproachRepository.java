package es.dgc.gesco.service.repository;

import es.dgc.gesco.model.modules.approach.db.entity.Approach;
import es.dgc.gesco.model.modules.user.dto.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApproachRepository
        extends BaseRepository<Approach, Long>,
        QueryByCriteria<Approach, UserCriteria> {

    @Override
    List<Approach> findAll();

    // @Query(value = "SELECT ap FROM Approach ap WHERE  DATE(ap.date) =':#{#fecha}' ")
    //   @Query("SELECT ap FROM Approach ap WHERE FUNCTION('DATE', ap.date) = :fecha")
//    @Query("SELECT ap FROM Approach ap WHERE CAST(ap.date AS date) = :fecha")
//    Optional<Approach> getApproachByDate(@Param("fecha") LocalDate fecha);
//    @Query(value ="SELECT ap FROM Approach ap WHERE ap.date = :fecha")
//    Page<Approach> getApproachByDate(@Param("fecha") LocalDate fecha);

    @Query(value ="SELECT ap FROM Approach ap WHERE ap.autonomousCommunityId =:#{#idCa}")
    Page<Approach> getApproachByAutonomousCommunityId(@Param("idCa") Long idCa, Pageable pageable);


    @Query(value ="SELECT ap FROM Approach ap WHERE ap.date BETWEEN :#{#localDateIni} AND :#{#localDateFin}")
    Page<Approach> getApproachByDate(@Param("localDateIni") LocalDate localDateIni, @Param("localDateFin") LocalDate localDateFin, Pageable pageable);
}