package com.blaizmiko.popcornapp.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class FormatUtil {

    private static final String ONE_DECIMAL = "0.0";

    public static float roundToOneDecimal(final double number) {
        return Float.parseFloat(new DecimalFormat(FormatUtil.ONE_DECIMAL, new DecimalFormatSymbols(Locale.US)).format(number));
    }

    public static float fromTenToFivePointScale(final double number) {
        final double fiveScaleNumber = number / 2;
        return (float) fiveScaleNumber;
    }
}
