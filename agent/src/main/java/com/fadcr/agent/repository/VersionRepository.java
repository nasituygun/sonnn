package com.fadcr.agent.repository;

import com.fadcr.agent.entity.Version;

import reactor.core.publisher.Flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VersionRepository extends ReactiveMongoRepository<Version,String> {

    Mono<Version> findByName(String versionName);

    Mono<Boolean> existsByName(String versionName);



}
