package com.example.versioning.rest.publication.v2;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Map;
import java.util.Set;

@Builder
@Data
@Relation(itemRelation = "publication", collectionRelation = "publications")
public class PublicationV2Representation extends RepresentationModel<PublicationV2Representation> {

    private Set<String> tags;

    private Map<String, String> labels;

    private String title;

    private String content;

}
