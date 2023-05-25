package com.fadcr.agent.service;

import com.fadcr.agent.config.ConfigFile;
import com.fadcr.agent.data.converter.VersionConverter;
import com.fadcr.agent.data.response.GitHubCommitResponse;
import com.fadcr.agent.data.response.VersionDTO;
import com.fadcr.agent.entity.Project;
import com.fadcr.agent.entity.Version;
import com.fadcr.agent.repository.VersionRepository;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GithubService {

    private final RestTemplate restTemplate;
    private final ProjectService projectService;
    private final VersionService versionService;
    private final ConfigFile configFile;
   ;

    public GithubService(RestTemplate restTemplate, ProjectService projectService,
                         VersionService versionService, ConfigFile configFile) {
        this.restTemplate = restTemplate;
        this.projectService = projectService;
        this.versionService = versionService;
        this.configFile = configFile;

    }

    public List<VersionDTO> getVersion(){
        fetchVersion();
        List<Version> versions= versionService.getAll();
        List<VersionDTO> versionDTOs=new ArrayList<>();
        for (Version version: versions){
            versionDTOs.add(VersionConverter.convert(version));
        }
        return versionDTOs;
    }
    public void fetchVersion() {
        String url = String.format(configFile.getProjectRepoApiUrl() + "/tags?page=%d", 1);
        List<Version> versions = new ArrayList<>();
        int page = 1;
        Project project = projectService.findByName(configFile.getRepoName()).block();
        if (project == null) {
            project = projectService.createProject();
        }

        ExecutorService executor = Executors.newFixedThreadPool(100);

        while (true) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0");
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Bearer " + configFile.getProjectRepoToken());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
            if (response.getStatusCodeValue() != 200) {
                System.out.println("Error response code: " + response.getStatusCodeValue());
                break;
            }
            String responseBody = response.getBody();
            Version[] tags = new Gson().fromJson(responseBody, Version[].class);
            if (tags.length == 0) {
                break;
            }
            for (Version version : tags) {
                executor.execute(() -> {
                    if (!versionService.isExitsByName(version.getName())) {
                        fetchCommitMessage(version.getCommit().getSha(),version);
                        synchronized (versions) {
                            versions.add(version);
                        }
                    }
                });
            }
            page++;
            url = String.format(configFile.getProjectRepoApiUrl() + "/tags?page=%d", page);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Thread error");
            Thread.currentThread().interrupt();
        }

        versionService.saveVersions(versions);
    }




    public void fetchCommitMessage(String commitSha,Version version) {
        String url = String.format(configFile.getProjectRepoApiUrl()) + "/commits/" + commitSha;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + configFile.getProjectRepoToken());
        headers.set("Accept", "application/vnd.github+json");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<GitHubCommitResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, GitHubCommitResponse.class);
        version.setDescription(responseEntity.getBody().getCommit().getMessage());
        version.setCreatedAt(responseEntity.getBody().getCommit().getDate());
    }






}









