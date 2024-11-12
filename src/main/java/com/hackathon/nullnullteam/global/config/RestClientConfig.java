package com.hackathon.nullnullteam.global.config;

import java.time.Duration;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient restClient() {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings
            .DEFAULTS
            .withReadTimeout(Duration.ofSeconds(2))
            .withConnectTimeout(Duration.ofSeconds(5));

        return RestClient.builder()
            .requestFactory(ClientHttpRequestFactories.get(settings))
            .build();
    }
}
