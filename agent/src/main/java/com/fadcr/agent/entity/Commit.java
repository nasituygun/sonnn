package com.fadcr.agent.entity;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("Commits")
@Data
public class Commit implements Serializable {


    private String sha;
    private String url;
}
