package com.example.versioning.rest;

import com.example.versioning.rest.publication.v1.PublicationV1Representation;
import com.example.versioning.rest.publication.v2.PublicationV2Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RootController {

    @Autowired
    private LinkRelationProvider linkProvider;

    @GetMapping("/")
    public EntityModel<Map<?, ?>> get() {
        LinkRelation msgRel = linkProvider.getCollectionResourceRelFor(PublicationV1Representation.class);
        EntityModel<Map<?, ?>> model = EntityModel.of(Map.of());
        // Since we're implementing versioning via content negotiation, the created link will always be the same. It
        // probably wouldn't make any difference whether to choose PublicationV1Controller or PublicationV2Controller.
        // As a principle, we define that the latest API version should be used unless specified otherwise, so we point
        // that to v2.
        model.add(WebMvcLinkBuilder.linkTo(methodOn(PublicationV2Controller.class).getAll()).withRel(msgRel));
        return model;
    }

}