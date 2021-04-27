package no.maddin.apiclient;

import lombok.extern.java.Log;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TimeControllerApi;

import java.util.logging.Level;

@Log
public class TimeClient {
    public static void main(String[] args) {

        try {
            new TimeControllerApi().nowUTC();
        } catch (ApiException e) {
            log.log(Level.SEVERE, "Error calling time API", e);
        }
    }
}
