package com.fadcr.master.repository;

import com.fadcr.master.entity.Project;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface ProjectRepository extends ReactiveMongoRepository<Project, Long> {


}
