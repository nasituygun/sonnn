package com.fadcr.agent.controller;

import com.fadcr.agent.entity.method.Metric;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fadcr.agent.service.AnalyzeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/analyze")

public class AnalyzeController {
    private final AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @GetMapping("/{versionName}/{method}")

    public ResponseEntity<HttpStatus> analyzeProject(@PathVariable String versionName, @PathVariable String method ) {
        System.out.println("test");
        analyzeService.startAnalyze(versionName, method);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/result/{versionName}/{method}/page={page}/size={size}")
    public ResponseEntity<List<Metric>> getResult(
            @PathVariable String versionName,
            @PathVariable String method,
            @PathVariable int page,
            @PathVariable int size
    ) {
        List<Metric> metrics = analyzeService.getAnalysisResult(versionName, method, page, size);
        return ResponseEntity.ok(metrics);
    }



    
}
