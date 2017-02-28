package com.blaizmiko.popcornapp.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MovieUtils {

    public static float convertApiRatingToAppRating(final double rating) {
        return Float.parseFloat(new DecimalFormat(FormatUtils.ONE_DECIMAL, new DecimalFormatSymbols(Locale.US)).format(rating / 2));
    }
}
