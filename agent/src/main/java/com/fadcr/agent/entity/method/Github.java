package com.fadcr.agent.entity.method;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder

public class Github extends Method{

    @Builder.Default
    private String methodName="Github";


}
