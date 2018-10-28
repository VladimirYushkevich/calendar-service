package com.outfittery.calendar.utils;

import com.outfittery.calendar.models.StylistAvailability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class TimeSlotUtils {

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

    public static Collector<StylistAvailability, StringBuilder, String> combineAvailability() {
        return Collector.of(
                StringBuilder::new,
                (result, dto) -> {
                    String andResult;
                    final String availability = "b" + dto.getEncodedTimeSlots();
                    if (result.length() == 0) {
                        andResult = availability;
                    } else {
                        final int radix = 16;
                        final BigInteger left = new BigInteger(result.toString(), radix);
                        final BigInteger right = new BigInteger(availability, radix);
                        andResult = left.and(right).toString(radix);
                        log.debug("{} AND {} = {}", result, availability, andResult);
                        result.setLength(0);
                    }
                    result.append(andResult);
                },
                (left, right) -> {
                    left.append(right);
                    return left;
                },
                (result) -> result.substring(1)
        );
    }
}
