package es.consumo.gescom.commons.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GESCOMRepository<E, I> extends JpaRepository<E, I>, ReadOnlyRepository<E, I> {


}
