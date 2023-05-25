package com.fadcr.master.entity;

import lombok.*;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document(collection = "project")
public class Project implements Serializable {

    @Id
    private String id;

    private String owner;

    private String repoName;

    private String status;

    @Field("Version")
    private List<Version> version;

    private String about;

    private String readme;









}
