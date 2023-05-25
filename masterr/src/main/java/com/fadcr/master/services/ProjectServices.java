package com.fadcr.master.services;

import com.fadcr.master.dto.response.ProjectProfileResponse;
import com.fadcr.master.entity.Project;
import com.fadcr.master.repository.ProjectRepository;

import java.util.List;

public class ProjectServices {

    private final ProjectRepository projectRepository;

    public ProjectServices(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void saveProject(Project project) {

        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).block();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll() .collectList()
                .block();
    }







}
