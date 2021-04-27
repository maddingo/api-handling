package no.maddin.apiclient;

import org.openapitools.client.api.TimeControllerApi;
import org.springframework.web.client.RestClientException;

public class TimeClient {
    public static void main(String[] args) {

        try {
            new TimeControllerApi().nowUTC();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
