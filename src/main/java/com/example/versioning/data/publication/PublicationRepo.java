package com.example.versioning.data.publication;

import com.example.versioning.data.publication.Publication;
import org.springframework.data.repository.CrudRepository;

public interface PublicationRepo extends CrudRepository<Publication, String> {
}
