package com.aklimets.pet.util.datetime;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TimeSource {

    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public long getCurrentMills() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
