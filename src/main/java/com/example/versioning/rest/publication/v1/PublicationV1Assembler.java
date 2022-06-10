package com.example.versioning.rest.publication.v1;

import com.example.versioning.data.publication.Publication;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PublicationV1Assembler implements RepresentationModelAssembler<Publication, PublicationV1Representation> {

    @Override
    public PublicationV1Representation toModel(Publication entity) {
        PublicationV1Representation rep = PublicationV1Representation.builder()
                .tags(entity.getTags())
                .labels(entity.getLabels())
                .title(entity.getTitle())
                .text(entity.getContent())
                .build();
        rep.add(linkTo(methodOn(PublicationV1Controller.class).getOne(entity.getId())).withSelfRel());
        return rep;
    }
    @Override
    public CollectionModel<PublicationV1Representation> toCollectionModel(Iterable<? extends Publication> entities) {
        CollectionModel<PublicationV1Representation> reps = RepresentationModelAssembler.super.toCollectionModel(entities);
        reps.add(linkTo(methodOn(PublicationV1Controller.class).getAll()).withSelfRel());
        return reps;
    }

}
