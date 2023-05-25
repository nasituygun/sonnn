package com.fadcr.master.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectProfileResponse implements Serializable {

    private String name;

    private String description;

    private List<Method> methods;

    private String date;
}
