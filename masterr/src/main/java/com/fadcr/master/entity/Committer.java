package com.fadcr.master.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document(collection = "committer")
public class Committer implements Serializable {

    @Id
    private Long id;

    private String committer;


}
