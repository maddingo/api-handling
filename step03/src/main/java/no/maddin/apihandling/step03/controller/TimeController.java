package no.maddin.apihandling.step03.controller;

import no.maddin.apihandling.step03.api.TimeControllerApi;
import no.maddin.apihandling.step03.model.TimeResponse;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController implements TimeControllerApi {

    @Override
    public TimeResponse nowUtc() {
        return new TimeResponse(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
