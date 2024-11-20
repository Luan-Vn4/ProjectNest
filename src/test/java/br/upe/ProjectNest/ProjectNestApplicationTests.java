package br.upe.ProjectNest;

import br.upe.ProjectNest.infrastructure.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes=TestConfig.class)
class ProjectNestApplicationTests {

	@Test
	void contextLoads() {
	}

}
