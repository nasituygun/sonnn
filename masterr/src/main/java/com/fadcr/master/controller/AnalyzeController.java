package com.fadcr.master.controller;


import com.fadcr.master.services.AnalyzeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analyze")
public class AnalyzeController {
    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @GetMapping("/project_info/{agent_id}/{version}/{method}")
    public ResponseEntity<?> startAnalyse(String agent_id,String version, String method) {
        analyzeService.analyze(agent_id, version, method);
        return ResponseEntity.ok("Analyze has been started");
    }

    @GetMapping("/project_info/result/{agent_id}/{version}/{method}/page={page}/size={size}")
    public ResponseEntity<?> gelResult(String agent_id,String version, String method,int page,int size) {

        return ResponseEntity.ok(analyzeService.getAnalyze(agent_id, version, method,page,size));
    }










}
