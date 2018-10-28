package com.outfittery.calendar.utils;

import com.outfittery.calendar.models.StylistAvailability;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.outfittery.calendar.utils.TimeSlotUtils.combineAvailability;
import static com.outfittery.calendar.utils.TimeSlotUtils.getTimeSlots;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class TimeSlotUtilsTest {

    private Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    final Date tomorrow = Date.from(today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            .plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void getListOfTimeSlots() {
        assertArrayEquals(Arrays.asList(
                "09:00",
                "09:30",
                "10:00",
                "10:30",
                "11:00",
                "11:30",
                "12:00",
                "12:30",
                "13:00",
                "13:30",
                "14:00",
                "14:30",
                "15:00",
                "15:30",
                "16:00",
                "16:30").toArray(), getTimeSlots(9, 17, 16).toArray());
    }

    @Test
    public void computeAvailability() {
        final List<StylistAvailability> stylistAvailabilities = Arrays.asList(
                StylistAvailability.builder().encodedTimeSlots("1000100000000010").day(today).build(),
                StylistAvailability.builder().encodedTimeSlots("1000100100000010").day(today).build(),
                StylistAvailability.builder().encodedTimeSlots("1000010000000010").day(today).build(),
                StylistAvailability.builder().encodedTimeSlots("1010101010101010").day(tomorrow).build(),
                StylistAvailability.builder().encodedTimeSlots("0101010101010101").day(tomorrow).build()
        );

        final Map<Date, String> availabilityByDate = stylistAvailabilities.stream()
                .collect(groupingBy(StylistAvailability::getDay, combineAvailability()));

        assertThat(availabilityByDate.get(today), is("1000000000000010"));
        assertThat(availabilityByDate.get(tomorrow), is("0000000000000000"));
    }
}
