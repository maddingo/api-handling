package no.maddin.apiclient;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TimeControllerApi;
import org.openapitools.client.model.TimeResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.startsWith;

public class TimeClientTest {

    @Test
    void clientTest() throws ApiException {
        TimeResponse nowUTC = new TimeControllerApi().nowUTC();

        assertThat(nowUTC, hasProperty("timeString", startsWith("2021")));
    }
}
