package com.fadcr.master.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Method implements Serializable {
    private String methodName;
    private String methodStatus;

}
