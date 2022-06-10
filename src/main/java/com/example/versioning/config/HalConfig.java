package com.example.versioning.config;

import com.example.versioning.rest.publication.v1.PublicationV1Controller;
import com.example.versioning.rest.publication.v2.PublicationV2Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;

@Configuration
public class HalConfig {

    @Bean
    public HalConfiguration halConfiguration() {
        return new HalConfiguration()
            .withMediaType(MediaType.APPLICATION_JSON)
            .withMediaType(MediaTypes.HAL_JSON)
            .withMediaType(PublicationV1Controller.PUBLICATION_V1)
            .withMediaType(PublicationV2Controller.PUBLICATION_V2);
    }

}
