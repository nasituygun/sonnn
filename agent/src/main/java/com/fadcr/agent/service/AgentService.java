package com.fadcr.agent.service;

import com.fadcr.agent.config.ConfigFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class AgentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ConfigFile configFile ;


    public void sendRegisterRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ip", configFile.getAgentIpAddress());
        requestBody.put("port", configFile.getPort());
        Map<String, Object> project = new HashMap<>();
        project.put("owner", configFile.getProjectOwner());
        project.put("repoName", configFile.getRepoName());
        project.put("about", configFile.getProjectAbout());
        requestBody.put("project", project);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        restTemplate.postForObject(configFile.getMasterServerIpAddress(), request, String.class);
    }
}
