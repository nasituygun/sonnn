package com.fadcr.agent.service;

import com.fadcr.agent.config.ConfigFile;
import com.fadcr.agent.entity.method.Github;
import com.fadcr.agent.entity.method.Metric;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fadcr.agent.entity.Version;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    
public class AnalyzeService {


    private final ProjectService projectService;
    private final VersionService versionService;
    private final RestTemplate restTemplate;
    private final ConfigFile configFile;

    public AnalyzeService(ProjectService projectService, VersionService versionService, RestTemplate restTemplate, ConfigFile configFile) {
        this.projectService = projectService;
        this.versionService = versionService;
        this.restTemplate = restTemplate;
        this.configFile = configFile;
    }

    public void startAnalyze(String versionName, String method){
        Version version = versionService.findByVersionName(versionName);
        version.getMethod(method).setMethodStatus("Analyzing");
        versionService.saveVersionBlock(version);
        analyzeProject(version,method);
        System.out.println("test");
    }


    public void analyzeProject(Version version, String method) {

        Map<String, Object> data = new HashMap<>();
        data.put("repo_owner", configFile.getProjectOwner());
        data.put("repo_name", configFile.getRepoName());
        data.put("password", configFile.getProjectRepoToken());
        data.put("commit_sha", version.getCommit().getSha());


        WebClient webClient=WebClient
                .builder()
                .baseUrl("http://127.0.0.1:80")
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(1024 * 1024*1024))
                        .build())
                .build();

        try {
            String response = webClient.post()
                    .uri("/clone_repo/")
                    .body(BodyInserters.fromValue(data))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Metric> metrics = objectMapper.readValue(response.toLowerCase(), new TypeReference<List<Metric>>() {});

            version.getMethod(method).setMethodStatus("azalyzed");
            version.getMethod(method).setMetrics(metrics);
            versionService.saveVersion(version).block();

        } catch (JsonProcessingException e) {
            version.getMethod(method,0,10).setMethodStatus("failed");
            versionService.saveVersion(version);
            throw new RuntimeException(e);
        }







    }
    public List<Metric> getAnalysisResult(String versionName, String method,int page,int size){
        Version version = versionService.findByVersionName(versionName);
        return version.getMethod(method,page,size).getMetrics();
    }


    
}
