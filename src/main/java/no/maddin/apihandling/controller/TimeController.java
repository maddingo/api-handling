package no.maddin.apihandling.controller;

import no.maddin.apihandling.model.TimeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController {

    @GetMapping("/now/utc")
    public TimeResponse nowUTC() {
        return TimeResponse.builder()
            .timeString(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
            .build();
    }
}
