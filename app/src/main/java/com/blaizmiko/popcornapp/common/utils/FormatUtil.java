package com.blaizmiko.popcornapp.common.utils;

import android.support.annotation.NonNull;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static float fromFiveToTenPointScale(final double number) {
        return (float) number * 2;
    }

    @NonNull
    public static String materialDateFormatParse (@NonNull final String sourceDate) {
        final String SOURCE_PATTERN = "yyyy-MM-dd";
        final String MATERIAL_PATTERN = "dd MMMM yyyy";

        String result;
        try {
            final SimpleDateFormat format = new SimpleDateFormat(SOURCE_PATTERN);
            final Date date = format.parse(sourceDate);
            result = new SimpleDateFormat(MATERIAL_PATTERN).format(date);
        } catch (ParseException e) {
            result = sourceDate;
        }
        return result;
    }
}
