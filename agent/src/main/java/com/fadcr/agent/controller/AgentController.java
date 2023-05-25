package com.fadcr.agent.controller;

import com.fadcr.agent.service.GithubService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController{


    public AgentController() {

    }

    @GetMapping("/initialize")
    public ResponseEntity<?> initialize() {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/is_online")
    public ResponseEntity<String> isOnline() {


        return ResponseEntity.ok("Agent is online");
    }



}
