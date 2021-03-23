package no.maddin.apihandling.controller;

import org.hamcrest.Matchers;
import org.hamcrest.io.FileMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenApiGeneratorTest {

    @LocalServerPort
    private int webPort;

    @Autowired
    private TestRestTemplate rest;

    @Test
    void generateOpenApiYaml() throws IOException {
        ResponseEntity<String> openApiResponse = rest.getForEntity("http://localhost:" + webPort + "/v3/api-docs.yaml", String.class);

        assertThat(openApiResponse, hasProperty("statusCodeValue", equalTo(200)));
        assertThat(openApiResponse, hasProperty("body", instanceOf(String.class)));

        ByteArrayInputStream inStream = new ByteArrayInputStream(requireNonNull(openApiResponse.getBody()).getBytes(StandardCharsets.UTF_8));
        Files.copy(inStream, Path.of("target/openapi.yaml"), StandardCopyOption.REPLACE_EXISTING);

        assertThat(new File("target/openapi.yaml"), FileMatchers.aFileWithSize(greaterThan(10L)));
    }
}
