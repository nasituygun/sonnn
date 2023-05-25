package com.fadcr.master.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate RestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return new RestTemplate();

    }
}