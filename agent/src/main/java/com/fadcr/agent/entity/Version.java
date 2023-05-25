package com.fadcr.agent.entity;

import java.io.Serializable;
import java.util.List;

import com.fadcr.agent.entity.method.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Version")

public class Version implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String createdAt;


    private String description;

    @Field("commit")
    private Commit commit;


    @Field("methods")
    @Builder.Default
    private List<Method> methods=List.of(BugCathcher.builder().build(),
            Github.builder().build(),
            Promise.builder().build());


    public Method getMethod(String methodName, int page, int size) {
        Method method = null;
        for (Method m : methods) {
            if (m.getMethodName().equals(methodName)) {
                method = m;
                break;
            }
        }

        if (method != null) {
            List<Metric> metrics = method.getMetrics();
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, metrics.size());
            List<Metric> paginatedMetrics = metrics.subList(startIndex, endIndex);
            method.setMetrics(paginatedMetrics);
        }

        return method;
    }

    public Method getMethod(String methodName) {
        for (Method method : methods) {
            if (method.getMethodName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

}
