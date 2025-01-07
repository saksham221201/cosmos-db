package com.saksham.cosmos.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(CosmosProperties.class)
@EnableCosmosRepositories(basePackages = "com.nagarro.cosmos.repository")
@PropertySource("classpath:application.properties")
public class CosmosDbConfig extends AbstractCosmosConfiguration {

    @Autowired
    private CosmosProperties properties;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        System.out.println("Cosmos URI: " + properties.getUri());
        System.out.println("Cosmos Key: " + properties.getKey());
        return new CosmosClientBuilder()
                .endpoint(properties.getUri())
                .key(properties.getKey())
                .gatewayMode(); // Use gateway mode if desired (or change to direct connection)
    }

    @Override
    protected String getDatabaseName() {
        return "Products";
    }
}