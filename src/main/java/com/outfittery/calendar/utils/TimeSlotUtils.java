package com.outfittery.calendar.utils;

import com.outfittery.calendar.models.Availability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class TimeSlotUtils {

    //TODO think about extract it to properties
    private static final int START_HOUR = 9;
    private static final int END_HOUR = 17;
    private static final int NUMBER_OF_SLOTS = 16;

    public static List<String> getTimeSlotsFromTimeIntervals() {
        final ArrayList<String> timeSlots = new ArrayList<>(NUMBER_OF_SLOTS);

        final int startTimeInMinutes = START_HOUR * 60;
        final int endTimeInMinutes = END_HOUR * 60;
        final int intervalInMinutes = (endTimeInMinutes - startTimeInMinutes) / NUMBER_OF_SLOTS;
        for (int time = startTimeInMinutes; time < endTimeInMinutes; time += intervalInMinutes) {
            final String formattedTime = String.format("%02d:%02d", time / 60, time % 60);
            timeSlots.add(formattedTime);
        }

        return timeSlots;
    }

    public static Map<Integer, String> getTimeSlotsFromEncodedString(String encodedTimeSlots) {
        final List<String> timeSlots = getTimeSlotsFromTimeIntervals();

        return IntStream.range(0, timeSlots.size())
                .boxed()
                .filter(i -> encodedTimeSlots.charAt(i) == '0')
                .collect(toMap(Function.identity(), timeSlots::get));
    }

    public static Collector<Availability, StringBuilder, String> combineAvailability() {
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

    public static String updateEncodedTimeAvailability(String original, int timeIndex, String value) {
        if (!("0".equals(value) || "1".equals(value) && timeIndex < 16)) {
            throw new IllegalArgumentException("Only '0' or '1' value are allowed");
        }

        return original.substring(0, timeIndex) + value + original.substring(timeIndex + 1);
    }
}
