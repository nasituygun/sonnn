package com.fadcr.agent.entity.method;


import com.fadcr.agent.entity.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document("Methods")
public abstract class Method implements Serializable {

    @Field("differentMethodName")
    private String methodName;


    @Builder.Default
    private String methodStatus = "not analyzed";

    @Field("Metrics")
    private List<Metric> metrics;








}
