package no.maddin.apiclient;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.api.TimeControllerApi;
import org.springframework.web.client.RestClientException;

@Slf4j
public class TimeClient {
    public static void main(String[] args) {

        try {
            new TimeControllerApi().nowUTC()
            .subscribe(timeResponse -> log.info(timeResponse.getTimeString()))
            ;
        } catch (RestClientException e) {
            log.error("Error calling time API", e);
        }
    }
}
