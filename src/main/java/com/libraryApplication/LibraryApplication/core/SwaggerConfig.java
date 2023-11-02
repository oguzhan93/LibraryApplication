package com.libraryApplication.LibraryApplication.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "Library Application API",
                version = "1.0",
                description = "Library Application API Documentation",
                contact = @Contact(
                        name = "Oğuzhan SANCAR",
                        email = "oguzhansancar93@icloud.com",
                        url = "https://github.com/oguzhan93/LibraryApplication/")
        ),
        servers = {
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080"
                )
        }
)

public class SwaggerConfig {
}


