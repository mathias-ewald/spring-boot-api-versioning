package com.example.versioning.data.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

/**
 * Just to have some test data to demonstrate with
 */
@ChangeUnit(id = "DataInitializer", order = "001")
public class DataInitializer {

    @Execution
    public void initialize(MongoTemplate template) {
        // Create a publication that follows the "old" schema
        PublicationV1 pub = PublicationV1.builder()
            .id("p1")
            .title("Some Title")
            .text("In lorem ipsum")
            .tags(Set.of("Feature"))
            .labels(Map.of("foo", "bar"))
            .build();

        template.save(pub);
    }

    @RollbackExecution
    public void rollback() {

    }


    @Data
    @Builder
    @Document("publication")
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PublicationV1 {

        private String id;

        private boolean published = false;

        private Set<String> tags;

        private Map<String, String> labels;

        private String title;

        private String text;

    }
}


