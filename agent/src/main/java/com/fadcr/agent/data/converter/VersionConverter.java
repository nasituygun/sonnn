package com.fadcr.agent.data.converter;

import com.fadcr.agent.data.response.VersionDTO;
import com.fadcr.agent.entity.Version;

public class VersionConverter {

    public static VersionDTO convert(Version from){
        return VersionDTO.builder().id(from.getId()).name(from.getName()).createdAt(from.getCreatedAt()).
                bugCatchger(from.getMethod("BugCatcher").getMethodStatus())
                .github(from.getMethod("Github").getMethodStatus())
                .promise(from.getMethod("Promise").getMethodStatus())
                .build();

    }
}
