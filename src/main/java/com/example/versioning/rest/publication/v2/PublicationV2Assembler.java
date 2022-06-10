package com.example.versioning.rest.publication.v2;

import com.example.versioning.data.publication.Publication;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PublicationV2Assembler implements RepresentationModelAssembler<Publication, PublicationV2Representation> {

    @Override
    public PublicationV2Representation toModel(Publication entity) {
        PublicationV2Representation rep = PublicationV2Representation.builder()
                .tags(entity.getTags())
                .labels(entity.getLabels())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
        rep.add(linkTo(methodOn(PublicationV2Controller.class).getOne(entity.getId())).withSelfRel());
        return rep;
    }
    @Override
    public CollectionModel<PublicationV2Representation> toCollectionModel(Iterable<? extends Publication> entities) {
        CollectionModel<PublicationV2Representation> reps = RepresentationModelAssembler.super.toCollectionModel(entities);
        reps.add(linkTo(methodOn(PublicationV2Controller.class).getAll()).withSelfRel());
        return reps;
    }

}
