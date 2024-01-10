package es.consumo.junta_arbitral.commons.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JJAARepository<E, I> extends JpaRepository<E, I>, ReadOnlyRepository<E, I> {


}
