package com.fadcr.master.dto;

import com.fadcr.master.dto.request.ProjectDto;
import com.fadcr.master.dto.request.Register;
import com.fadcr.master.entity.Agent;
import com.fadcr.master.entity.Project;

public class Converter {

    public static Agent toEntity(Register dto) {
        Agent agent = Agent.builder().ip(dto.getIp()).
                port(dto.getPort()).project(toEntity(dto.getProject())).build();
        return agent;
    }

    public static Project toEntity(ProjectDto dto) {
        Project project = Project.builder().status("not analyzed").
                owner(dto.getOwner()).
                repoName(dto.getRepoName()).
                about(dto.getAbout()).
                build();
        return project;
    }
}