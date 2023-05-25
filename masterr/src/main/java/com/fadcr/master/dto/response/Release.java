package com.fadcr.master.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Release {
    private String versionValue;
    private String versionDescription;
    private String versionDate;
    private List<Method> methods;

}