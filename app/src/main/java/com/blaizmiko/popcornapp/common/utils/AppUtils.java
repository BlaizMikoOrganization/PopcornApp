package com.blaizmiko.popcornapp.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class AppUtils {
    public static final int ApiRatingToAppRating = 2;
    public static float roundToOneDecimal(final double number, final double converter) {
        return Float.parseFloat(new DecimalFormat(FormatUtils.ONE_DECIMAL, new DecimalFormatSymbols(Locale.US)).format(number / converter));
    }
}
