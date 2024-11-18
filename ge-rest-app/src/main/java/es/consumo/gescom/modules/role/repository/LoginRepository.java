package es.consumo.gescom.modules.role.repository;


import es.consumo.gescom.commons.db.repository.GESCOMRepository;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends GESCOMRepository<LoginEntity, Long> {

	@Query("SELECT u FROM LoginEntity u where lower(u.username) =lower(:username)")
    Optional<LoginEntity> findByUsername(String username);
	
	@Query("SELECT u FROM LoginEntity u where lower(u.username) =lower(:username) AND u.id != :id")
    Optional<LoginEntity> findByUsernameAndIdNotIn(String username, Long id);

    @Query("SELECT u FROM LoginEntity u where u.id = :id")
    List<LoginEntity> findByLoginId(Long id);
}
