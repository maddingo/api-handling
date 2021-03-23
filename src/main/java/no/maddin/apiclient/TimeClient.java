package no.maddin.apiclient;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.TimeControllerApi;

public class TimeClient {
    public static void main(String[] args) {

        try {
            new TimeControllerApi().nowUTC();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
