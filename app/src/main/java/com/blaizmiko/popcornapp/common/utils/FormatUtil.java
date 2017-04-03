package com.blaizmiko.popcornapp.common.utils;

import android.content.Context;
import android.widget.Toast;

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

    public static String niceFormatDateParse(final Context context, String sourceDate) {
        final String NOT_PARSABLE = "Can't parse date";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(sourceDate);
            sourceDate = new SimpleDateFormat("MMMM dd, yyyy").format(date);
        } catch (ParseException exception) {
            Toast.makeText(context, NOT_PARSABLE, Toast.LENGTH_SHORT).show();
        }
        return sourceDate;
    }
}
