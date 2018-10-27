package com.outfittery.calendar.helper;

import org.junit.Test;

import java.util.Arrays;

import static com.outfittery.calendar.helper.TimeSlotConverter.getTimeSlots;
import static org.junit.Assert.assertArrayEquals;

public class TimeSlotConverterTest {

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
}
