package com.outfittery.calendar.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class TimeSlotConverter {

    static List<String> getTimeSlots(int startHour, int endHour, int numberOfSlots) {
        final ArrayList<String> timeSlots = new ArrayList<>(numberOfSlots);

        final int startTimeInMinutes = startHour * 60;
        final int endTimeInMinutes = endHour * 60;
        final int intervalInMinutes = (endTimeInMinutes - startTimeInMinutes) / numberOfSlots;
        for (int time = startTimeInMinutes; time < endTimeInMinutes; time += intervalInMinutes) {
            final String formattedTime = String.format("%02d:%02d", time / 60, time % 60);
            timeSlots.add(formattedTime);
        }

        return timeSlots;
    }

    static byte[] toBits(Short value) {

        final int numberOfBits = 16;
        final byte[] bits = new byte[numberOfBits];
        final char[] binaryChars = Integer.toBinaryString(value).toCharArray();
        for (int i = 0; i < numberOfBits; i++) {
            byte bit = 0;
            if (i < binaryChars.length) {
                bit = (byte) Character.getNumericValue(binaryChars[i]);
            }
            bits[i] = bit;
        }

        return bits;
    }

}
