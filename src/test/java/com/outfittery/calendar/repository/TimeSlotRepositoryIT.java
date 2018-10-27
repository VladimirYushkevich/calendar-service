package com.outfittery.calendar.repository;

import com.outfittery.calendar.models.TimeSlot;
import com.outfittery.calendar.repositories.TimeSlotRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.fail;

public class TimeSlotRepositoryIT extends BaseRepositoryIT {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Test
    public void shouldPersistValidAvailability() {
        entityManager.persistAndFlush(from("0000000000000001"));
    }

    @Test
    public void shouldFailOnNotValidAvailability() {
        checkAvailability("0000000000000002", "Has not allowed number");
        checkAvailability("000000000000000a", "Has not a number");
        checkAvailability("000010100000001", "Has less numbers");
        checkAvailability("00001010000000100", "Has more numbers");
    }

    private void checkAvailability(String availability, String reason) {
        try {
            entityManager.persistAndFlush(from(availability));
            fail(reason);
        } catch (ConstraintViolationException ignored) {
        }

        entityManager.clear();
    }

    private TimeSlot from(String availability) {
        final Date day = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        return TimeSlot.builder()
                .day(day)
                .availability(availability)
                .build();
    }
}
