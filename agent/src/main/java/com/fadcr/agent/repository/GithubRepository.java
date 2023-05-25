package com.fadcr.agent.repository;

import com.fadcr.agent.entity.GithubEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubRepository extends ReactiveMongoRepository<GithubEntity, String> {


}
