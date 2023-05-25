package com.fadcr.agent.entity.method;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Promise extends Method{

    @Builder.Default
    private String methodName = "Promise";
}
