package com.fadcr.master.services;

import com.fadcr.master.dto.Converter;
import com.fadcr.master.dto.request.Register;
import com.fadcr.master.entity.Agent;
import com.fadcr.master.repository.AgentRepository;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class RegisterService {

    private final AgentRepository agentRepository;

    public RegisterService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void register(Register agent) {
        Mono<Agent> agentMono = agentRepository.findByIpAndPort(agent.getIp(), agent.getPort());
        Agent agent1 = agentMono.block();

        if (agent1 != null) {
            Agent presentAgent=Converter.toEntity(agent);
            presentAgent.setId(agent1.getId());
            agentRepository.save(presentAgent).block();
        } else {
            agentRepository.save(Converter.toEntity(agent)).block();
        }
    }
}
