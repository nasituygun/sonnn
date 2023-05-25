package com.fadcr.master.controller;

import com.fadcr.master.dto.response.ProjectProfileResponse;
import com.fadcr.master.services.AgentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

    private final AgentServices agentServices;

    public AgentController(AgentServices agentServices) {
        this.agentServices = agentServices;
    }


    @GetMapping("/active")
    public ResponseEntity<?> getAgent() {

        return ResponseEntity.ok(agentServices.getActiveAgent());
    }
    @GetMapping("/project/{agentId}")
    public ResponseEntity<List<ProjectProfileResponse>> getProject(@PathVariable String agentId) {
        List<ProjectProfileResponse> projectVersions = agentServices.getAllVersions(agentId);
        return ResponseEntity.ok(projectVersions);
    }



/*    @GetMapping("/project_info/{agent_id}")
    public ResponseEntity<?> getProjectInfo(@PathVariable("agent_id") String agent_id) {
        return ResponseEntity.ok(agentServices.getProjectInfo(agent_id));
    }*/

}
