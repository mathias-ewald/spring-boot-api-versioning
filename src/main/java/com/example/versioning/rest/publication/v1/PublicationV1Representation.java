package com.example.versioning.rest.publication.v1;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Map;
import java.util.Set;

@Builder
@Data
@Relation(itemRelation = "publication", collectionRelation = "publications")
public class PublicationV1Representation extends RepresentationModel<PublicationV1Representation> {

    private Set<String> tags;

    private Map<String, String> labels;

    private String title;

    private String text;

}
