package com.fadcr.master.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Register {

    private String ip;

    private int port;

    private ProjectDto project;




}
