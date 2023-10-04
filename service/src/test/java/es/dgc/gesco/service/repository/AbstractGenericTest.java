package es.dgc.gesco.service.repository;

import es.dgc.gesco.service.ConstanteTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles(profiles = ConstanteTest.PERFIL_TEST)
@TestPropertySource(locations = ConstanteTest.PROPERTIES_TEST)
@Sql(ConstanteTest.DATA_SQL)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbstractGenericTest {}
