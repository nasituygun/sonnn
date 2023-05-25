package com.fadcr.agent.controller;

import com.fadcr.agent.data.response.VersionDTO;
import com.fadcr.agent.entity.Version;
import com.fadcr.agent.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/project")
public class ProjectController {

    private  final GithubService githubService;

    public ProjectController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/version")
    public ResponseEntity<List<VersionDTO>> getVersion() {
        System.out.println("getVersion");
        return ResponseEntity.ok(githubService.getVersion());

    }
}
