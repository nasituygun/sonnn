package com.fadcr.agent.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    private final ConfigFile configFile;

    public MongoConfig(ConfigFile configFile) {
        this.configFile = configFile;
    }

    @Override
    protected String getDatabaseName() {
        return configFile.getRepoName();
    }

    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }


    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        MappingMongoConverter converter = new MappingMongoConverter(
                (DbRefResolver) reactiveMongoDbFactory(), new MongoMappingContext());
        converter.setTypeMapper(null);
        return new ReactiveMongoTemplate(reactiveMongoDbFactory(), converter);
    }


}
