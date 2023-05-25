package com.fadcr.master.controller;

import com.fadcr.master.dto.request.Register;
import com.fadcr.master.services.RegisterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController

public class RegisterController {

    private final RegisterService registerServices;

    public RegisterController(RegisterService registerServices) {
        this.registerServices = registerServices;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register agent) {
        registerServices.register(agent);
        return new ResponseEntity<>("Register Success",HttpStatus.CREATED);
    }


}
