package com.fadcr.master.repository;

import com.fadcr.master.entity.Agent;

import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentRepository extends ReactiveMongoRepository<Agent, String> {

    Mono<Agent> findByIpAndPort(String ip, int port);



}
