package com.fadcr.agent.repository;

import com.fadcr.agent.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ProjectRepository extends ReactiveMongoRepository<Project,String> {

        Mono<Project> findByProjectName(String name);



}