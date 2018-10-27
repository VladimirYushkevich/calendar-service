package com.outfittery.calendar.helper;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TimeSlotConverterTest {

    private TimeSlotConverter timeSlotConverter = new TimeSlotConverter();

    @Test
    public void test() {
        assertArrayEquals(new byte[]{1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, timeSlotConverter.toBits((short) 42));
    }
}
