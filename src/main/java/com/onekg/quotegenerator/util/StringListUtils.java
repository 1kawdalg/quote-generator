package com.onekg.quotegenerator.util;

import java.util.Arrays;

public final class StringListUtils {
    private StringListUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String[] makeStringsLower(String[] source) {
        return Arrays.stream(source)
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }
}
