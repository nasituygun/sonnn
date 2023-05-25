package com.fadcr.agent.data.response;

import com.fadcr.agent.entity.method.BugCathcher;
import com.fadcr.agent.entity.method.Github;
import com.fadcr.agent.entity.method.Method;
import com.fadcr.agent.entity.method.Promise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionDTO {

    private String id;
    private String name;
    private String createdAt;
    private String description;
    private String github;
    private String bugCatchger;
    private String promise;


}
