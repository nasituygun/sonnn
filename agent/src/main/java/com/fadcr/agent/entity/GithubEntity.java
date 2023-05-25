package com.fadcr.agent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("github_entities")
public class GithubEntity {

    @Id
    private String id;
    private String readme;
    private String createdAt;
    private String release;
    private int numberOfCommits;
    private String contributors;
    private String owner;
    private String repoName;
}
