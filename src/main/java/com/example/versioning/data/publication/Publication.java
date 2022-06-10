package com.example.versioning.data.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

    private String id;

    private Set<String> tags;

    private Map<String, String> labels;

    private String title;

    private String content;

}
