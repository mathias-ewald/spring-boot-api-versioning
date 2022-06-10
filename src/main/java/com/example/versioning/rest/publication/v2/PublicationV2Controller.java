package com.example.versioning.rest.publication.v2;

import com.example.versioning.data.publication.Publication;
import com.example.versioning.data.publication.PublicationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@ExposesResourceFor(PublicationV2Representation.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicationV2Controller {

    public static final String PUBLICATION_V2_VALUE = "application/vnd.example.pub.v2+json";

    public static final MediaType PUBLICATION_V2 = MediaType.valueOf(PUBLICATION_V2_VALUE);


    @Autowired
    private PublicationV2Assembler publicationAssembler;

    private final PublicationRepo repo;

    @RequestMapping(
        path = "/publications",
        method = RequestMethod.GET,
        produces =  {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE, PUBLICATION_V2_VALUE}
    )
    public CollectionModel<PublicationV2Representation> getAll() {
        return publicationAssembler.toCollectionModel(repo.findAll());
    }

    @RequestMapping(
        path = "/publications/{id}",
        method = RequestMethod.GET,
        produces =  {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE, PUBLICATION_V2_VALUE}
    )
    public PublicationV2Representation getOne(@PathVariable String id) {
        Optional<Publication> pub = repo.findById(id);
        return publicationAssembler.toModel(
            pub.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"))
        );
    }

}