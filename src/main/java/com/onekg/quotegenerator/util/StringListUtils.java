package com.onekg.quotegenerator.util;

import java.util.Arrays;

public class StringListUtils {

    public static String[] makeStringsLower(String[] source) {
        return Arrays.stream(source)
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }
}
