package com.fadcr.agent.service;

import com.fadcr.agent.config.ConfigFile;
import com.fadcr.agent.entity.Project;
import com.fadcr.agent.repository.ProjectRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProjectService {

    private final ConfigFile configFile;


    private final ProjectRepository projectRepository;

    public ProjectService(ConfigFile configFile, ProjectRepository projectRepository) {
        this.configFile = configFile;
        this.projectRepository = projectRepository;
    }


    public void saveProject(Project project) {

        projectRepository.save(project).block();
    }

    public Mono<Project> findByName(String name) {

        return projectRepository.findByProjectName(name);
    }

    public Project createProject() {
        Project project = Project.builder()
                .projectName(configFile.getRepoName())
                .projectDescription(configFile.getProjectAbout())
                .build();

        saveProject(project);
        return project;
    }



}
