package com.example.versioning.rest.publication.v1;

import com.example.versioning.data.publication.Publication;
import com.example.versioning.data.publication.PublicationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@ExposesResourceFor(PublicationV1Representation.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicationV1Controller {

    public static final String PUBLICATION_V1_VALUE = "application/vnd.example.pub.v1+json";

    public static final MediaType PUBLICATION_V1 = MediaType.valueOf(PUBLICATION_V1_VALUE);


    private final PublicationV1Assembler assembler;

    private final PublicationRepo repo;

    @RequestMapping(
        path = "/publications",
        method = RequestMethod.GET,
        produces =  {PUBLICATION_V1_VALUE}
    )
    public CollectionModel<PublicationV1Representation> getAll() {
        return assembler.toCollectionModel(repo.findAll());
    }

    @RequestMapping(
        path = "/publications/{id}",
        method = RequestMethod.GET,
        produces =  {PUBLICATION_V1_VALUE}
    )
    public PublicationV1Representation getOne(@PathVariable String id) {
        Optional<Publication> pub = repo.findById(id);
        return assembler.toModel(
            pub.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"))
        );
    }

}