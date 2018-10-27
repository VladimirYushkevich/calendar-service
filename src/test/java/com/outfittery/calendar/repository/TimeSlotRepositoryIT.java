package com.outfittery.calendar.repository;

import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.TimeSlot;
import com.outfittery.calendar.repositories.StylistRepository;
import com.outfittery.calendar.repositories.TimeSlotRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TimeSlotRepositoryIT extends BaseRepositoryIT {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private StylistRepository stylistRepository;

    private Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void shouldPersistValidTimeSlots() {
        final Date tomorrow = Date.from(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        entityManager.persistAndFlush(from(1L, today, "0000000000000001"));
        entityManager.persistAndFlush(from(2L, today, "0001001001000001"));
        entityManager.persistAndFlush(from(1L, tomorrow, "1010000000000001"));
    }

    @Test
    public void shouldFailOnNotValidTimeSlots() {
        check(1L, today, "0000000000000002", "Has not allowed number in availability");
        check(1L, today, "000000000000000a", "Has not a number in availability");
        check(1L, today, "000010100000001", "Has less than 16 numbers in availability");
        check(1L, today, "00001010000000100", "Has more than 16 numbers in availability");

        entityManager.persistAndFlush(from(2L, today, "0000000000000001"));
        check(2L, today, "1000101000000010", "For this day stylist has already time slots");
    }

    @Test
    public void searchByDate() {
        final Date todayPlusOne = Date.from(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        final Date todayPlusTwo = Date.from(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .plusDays(2).atZone(ZoneId.systemDefault()).toInstant());
        final Date todayPlusThree = Date.from(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .plusDays(3).atZone(ZoneId.systemDefault()).toInstant());

        entityManager.persistAndFlush(from(1L, today, "0000000000000001"));
        entityManager.persistAndFlush(from(2L, today, "0001001001000001"));
        entityManager.persistAndFlush(from(1L, todayPlusOne, "1111111011111111"));
        entityManager.persistAndFlush(from(1L, todayPlusTwo, "1010000000000001"));
        entityManager.persistAndFlush(from(2L, todayPlusTwo, "1111111111111111"));
        entityManager.persistAndFlush(from(3L, todayPlusThree, "1010000000000001"));

        assertThat(timeSlotRepository.findAllByDayBetweenAndAndAvailabilityContains(todayPlusOne, todayPlusTwo, "0").size(),
                is(2));
    }

    private void check(Long stylistId, Date day, String availability, String reason) {
        try {
            entityManager.persistAndFlush(from(stylistId, day, availability));
            fail(reason);
        } catch (ConstraintViolationException | PersistenceException ignored) {
            System.out.println();
        }

        entityManager.clear();
    }

    private TimeSlot from(Long stylistId, Date day, String availability) {
        final Stylist stylist = stylistRepository.findById(stylistId).orElseThrow(IllegalAccessError::new);

        return TimeSlot.builder()
                .stylist(stylist)
                .day(day)
                .availability(availability)
                .build();
    }
}
