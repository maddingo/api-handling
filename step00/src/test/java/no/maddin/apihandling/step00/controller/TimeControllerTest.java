package no.maddin.apihandling.step00.controller;

import no.maddin.apihandling.step00.model.TimeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static no.maddin.HasRecordComponentWithValue.*;

import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeControllerTest {
    @LocalServerPort
    int webPort;

    @Autowired
    TestRestTemplate rest;

    @Test
    void nowUTC() {
        ResponseEntity<TimeResponse> response = rest.getForEntity("http://localhost:" + webPort + "/now/utc", TimeResponse.class);

        String expectedYear = String.format("%d-", ZonedDateTime.now().getYear());
        assertThat(response,
            hasProperty("body",
                hasRecordComponent("timeString",
                    startsWith(expectedYear)
                )
            )
        );
    }

    @Test
    void checkOAS() {
        ResponseEntity<String> openApiResponse = rest.getForEntity("http://localhost:" + webPort + "/v3/api-docs.yaml", String.class);

        assertThat(openApiResponse, hasProperty("statusCode", equalTo(HttpStatus.OK)));
    }
}
