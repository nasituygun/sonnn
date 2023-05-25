package com.fadcr.master.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "metrics")

public class GithubEntity {

    private String lineOfCode;
    private String logicalLineOfCode;
    private String afferentCoupling;
    private String efferentCoupling;
    private String numberOfMethods;
    private String numberOfClasses;


}
