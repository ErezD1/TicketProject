package edu.erezd.erezproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition (info = @Info(
        title = "Ticket Project API",
        version = "V1.0.0",
        description = "Ticket Project API",
        contact = @Contact(email ="erezdagan5@gmail.com", name = "Erez Dagan", url = "https://www.linkedin.com/in/erez-dagan-5" ),
        license = @License(name = "Apache License Version 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")))
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearer Authentication",
        scheme = "bearer",
        bearerFormat = "JWT")

public class OpenApi3Config {
}
