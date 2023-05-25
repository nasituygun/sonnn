package com.fadcr.master.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Document(collection = "version")
@Data
public class Version implements Serializable {

    @Id

    private Long id;

    private String version;

    @Field("versionDetail")
    private VersionDetail versionDetail;



}
