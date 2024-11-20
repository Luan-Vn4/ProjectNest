package br.upe.ProjectNest.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

@TestConfiguration
@Profile("test")
@TestPropertySource("classpath:application-test.properties")
public class TestConfig {}
