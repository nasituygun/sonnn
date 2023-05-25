package com.fadcr.agent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@Getter
@Setter
public class ConfigFile {



    @Value("${server.port}")
    private  int port;


    @Value("${master.server.ip-address}")
    private  String masterServerIpAddress;

    @Value("${project.repo.url}")
    private String projectRepoUrl;

    @Value("${project.repo.token}")
    private String projectRepoToken;

    @Value("${project.version.strategy}")
    private String projectVersionStrategy;

    @Value("${project.about}")
    private String projectAbout;

    @Value("${agent.ip-address}")
    private String agentIpAddress;

    public String getRepoName() {

        try {
            URI uri = new URI(getProjectRepoUrl());
            String[] segments = uri.getPath().split("/");
            return segments[2];
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return RuntimeException.class.getName();
    }

    public String getProjectOwner() {

        try {
            URI uri = new URI(getProjectRepoUrl());
            String[] segments = uri.getPath().split("/");
            return segments[1];

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return RuntimeException.class.getName();
    }

    public String getProjectRepoApiUrl() {
        return "https://api.github.com/repos/" + getProjectOwner() + "/" + getRepoName();
    }


}
