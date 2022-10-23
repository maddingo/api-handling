package no.maddin.apihandling.step01.controller;

import no.maddin.apihandling.step01.model.TimeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static no.maddin.HasRecordComponentWithValue.hasRecordComponent;

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
}
