package com.fadcr.master.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder


public class AgentDto {

    private String id;

    private String owner;
    private String repositoryName;

    private String status;
    private String about;
    private boolean isOnline;








}
