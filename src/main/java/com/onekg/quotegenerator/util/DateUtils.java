package com.onekg.quotegenerator.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
