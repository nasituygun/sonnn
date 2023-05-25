package com.fadcr.agent.entity.method;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BugCathcher extends Method{

    @Builder.Default
    private String methodName = "BugCatcher";
}
