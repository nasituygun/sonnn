package com.fadcr.master.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@NoArgsConstructor

@Document(collection = "versionDetail")

public class VersionDetail {

    @Id
    private Long id;

    private String status;

    private String startTime;

    private String endTime;

    private String readme;

    @Field("Committer")
    private List<Committer> committers;

}
