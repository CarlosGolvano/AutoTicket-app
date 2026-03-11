package com.curso.autoticketapp.it.config;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RestConfig {

    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .rootUri("http://localhost:8080")
        );
    }
}