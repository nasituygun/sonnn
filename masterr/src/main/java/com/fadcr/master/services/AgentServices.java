package com.fadcr.master.services;

import com.fadcr.master.dto.response.AgentDto;
import com.fadcr.master.dto.response.ProjectProfileResponse;
import com.fadcr.master.dto.response.Release;
import com.fadcr.master.entity.Agent;
import com.fadcr.master.repository.AgentRepository;

import io.swagger.v3.oas.models.headers.Header;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AgentServices {

    private final RestTemplate rest;

    private final AgentRepository agentRepository;
    



    public AgentServices(RestTemplate rest, AgentRepository agentRepository) {
        this.rest = rest;
        this.agentRepository = agentRepository;


    }
    public List<AgentDto> getActiveAgent() {

        List<AgentDto> agentDtos = agentRepository.findAll()
                .map(agent -> AgentDto.builder()
                        .owner(agent.getProject().getOwner())
                        .repositoryName(agent.getProject().getRepoName())
                        .about(agent.getProject().getAbout())
                        .isOnline(isOnline(agent))
                        .id(agent.getId())
                        .build())
                .collectList()
                .block();

        return agentDtos;
    }

        public String getProjectInfo (String agentId){
            Agent agent = agentRepository.findById(agentId).block();
            String url = agent.getIp() + ":" + agent.getPort() + "/project_infos/";
            String response = rest.getForObject(url, String.class);
            return response;


        }

    public List<ProjectProfileResponse> getAllVersions(String agentId) {
        Agent agent = agentRepository.findById(agentId).block();
        String url =  agent.getIp() + ":" + agent.getPort() + "/project/version";
        System.out.println(url);
        ProjectProfileResponse[] projectResponses = rest.getForObject(url, ProjectProfileResponse[].class);
        return Arrays.asList(projectResponses);
    }



        private boolean isOnline(Agent agent) {
            String url =   agent.getIp() + ":" + agent.getPort() + "/agent/is_online";
            try {
                rest.getForObject(url, String.class);
                return true;
            } catch (RestClientException e) {
                System.out.println(e.getMessage() + " " + url);
                return false;
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + " " + url);
                return false;
            }
        }
        
}
