package no.maddin.apihandling.step03.controller;

import no.maddin.apihandling.step03.TimeControllerApi;
import no.maddin.apihandling.step03.model.TimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TimeController implements TimeControllerApi {
    @Override
    public ResponseEntity<TimeResponse> nowUTC() {
        return ResponseEntity.ok(new TimeResponse().timeString(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME)));
    }
}
