package com.outfittery.calendar.helper;

class TimeSlotConverter {

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
