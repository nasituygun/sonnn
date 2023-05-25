package com.fadcr.master.services;

import com.fadcr.master.entity.Agent;
import com.fadcr.master.repository.AgentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fadcr.agent.entity.method.AnalyzeEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AnalyzeService {

    private final RestTemplate restTemplate;
    private final AgentRepository agentRepository;

    public AnalyzeService(RestTemplate restTemplate,
                          AgentRepository agentRepository) {
        this.restTemplate = restTemplate;
        this.agentRepository = agentRepository;
    }

    public void analyze(String agentId, String version, String method) {
        agentRepository.findById(agentId).subscribe(agent -> {
            String url =  agent.getIp() + ":" + agent.getPort() + "/api/v1/analyze/"  + version + "/" + method;
            restTemplate.getForObject(url, String.class);

        });

    }

    public List<AnalyzeEntity> getAnalyze(String agentId, String version, String method,int page,int size) {
        Agent agent = agentRepository.findById(agentId).block(); // Agent'i almak için bloklayıcı çağrı

        if (agent != null) {
            String url = agent.getIp() + ":" + agent.getPort() + "/api/v1/analyze/result/" + version + "/" + method + "/page=" + page + "/size=" + size;            ResponseEntity<AnalyzeEntity[]> response = restTemplate.getForEntity(url, AnalyzeEntity[].class);
            AnalyzeEntity[] analyzeEntities = response.getBody();

            if (analyzeEntities != null) {
                return Arrays.asList(analyzeEntities);
            }
        }

        return Collections.emptyList();
    }



}
