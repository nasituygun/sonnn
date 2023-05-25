package com.fadcr.master.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
@Document(collection = "agent")

public class Agent implements Serializable {

    @Id
    private String id;

    
    private String ip;

    private int port;

    @Field("Project")
    private Project project;


}
