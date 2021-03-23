package no.maddin.apihandling.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@lombok.Builder
@NoArgsConstructor // for Jackson deserialization
@AllArgsConstructor
public class TimeResponse {
    String timeString;
}
