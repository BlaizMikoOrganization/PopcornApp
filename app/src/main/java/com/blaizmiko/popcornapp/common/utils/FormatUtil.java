package com.blaizmiko.popcornapp.common.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class FormatUtil {

    private static final String ONE_DECIMAL = "0.0";

    public static float roundToOneDecimal(final double number) {
        return Float.parseFloat(new DecimalFormat(FormatUtil.ONE_DECIMAL, new DecimalFormatSymbols(Locale.US)).format(number));
    }

    public static int calculatePassedYearsFromCurrent(final String dateString) {
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        Log.d("date str", " " +dateString);
        int previousYear = parseServerDateString(dateString).get(Calendar.YEAR);
        Log.d("tagger", ""+previousYear);
        return currentYear - previousYear;
    }

    private static Calendar parseServerDateString(final String serverDate) {
        final String SOURCE_PATTERN = "yyyy-MM-dd";
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat format = new SimpleDateFormat(SOURCE_PATTERN, Locale.ENGLISH);
        Log.d("serverDate ", "" +serverDate);
        try {
            calendar.setTime(format.parse(serverDate));
            Log.d("parseServerDateString", ""+calendar.get(Calendar.YEAR));
        } catch (ParseException ex) {
            calendar.setTime(Calendar.getInstance().getTime());
            Log.d("parseServerDateString", ex.getMessage());
        }
        return calendar;
    }

    public static String parseGender(final int gender) {
        final String GENDER_MALE = "Male";
        final String GENDER_FEMALE = "Female";
        String parsedGender = StringUtil.NOT_AVAILABLE_STRING;
        switch (gender) {
            case 1: parsedGender = GENDER_FEMALE;
                break;
            case 2: parsedGender = GENDER_MALE;
                break;
        }

        Log.d("gender", ""+parsedGender);

        return parsedGender;
    }

    public static float convertDPtoPX(final float dp, final Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp , context.getResources().getDisplayMetrics());
    }

    public static float fromTenToFivePointScale(final double number) {
        final double fiveScaleNumber = number / 2;
        return (float) fiveScaleNumber;
    }

    public static float fromFiveToTenPointScale(final double number) {
        return (float) number * 2;
    }

    @NonNull
    public static String parseDateToMaterialFormat(@NonNull final String sourceDate, ResultMaterialDateType type) {
        final String MATERIAL_DATE_PATTERN = "dd MMMM yyyy";
        final String MATERIAL_SHORT_DATE_PATTERN = "MMM yyy";
        final String SOURCE_PATTERN = "yyyy-MM-dd";

        String parsePattern;
        switch(type) {
            case FULL:
                parsePattern = MATERIAL_DATE_PATTERN;
                break;
            case SHORT: parsePattern = MATERIAL_SHORT_DATE_PATTERN;
                break;
            default: parsePattern = MATERIAL_DATE_PATTERN;
        }

        String result;
        try {
            final SimpleDateFormat format = new SimpleDateFormat(SOURCE_PATTERN);
            final Date date = format.parse(sourceDate);
            result = new SimpleDateFormat(parsePattern).format(date);
        } catch (ParseException e) {
            result = sourceDate;
        }
        return result;
    }

    public static String parseTimeToMaterialFormat(final int sourceTime) {
        final int minutesInHour = 60;
        int minutes = sourceTime % minutesInHour;
        int hours = (sourceTime - minutes) / minutesInHour;

        final String formattedHours = hours + SymbolUtil.SPACE + StringUtil.HOURS_ABBREVIATION_STRING;
        final String formattedMinutes = minutes + SymbolUtil.SPACE + StringUtil.MINUTES_ABBREVIATION_STRING;
        return formattedHours + SymbolUtil.SPACE + formattedMinutes;
    }

    public static String parseMoneyToMaterialFormat(final int money) {
        final String moneyString = Integer.toString(money);
        final int numbersAmount = moneyString.length();
        final int firstSpacePosition = numbersAmount  - numbersAmount % 3;
        return addSpacesToMoney(new StringBuffer(moneyString), firstSpacePosition);
    }

    private static String addSpacesToMoney(StringBuffer money, int currentPositionForInsert) {
        final int THOUSANDTH_DIGIT = 3;
        final int END_MONEY_LENGTH = 0;

        currentPositionForInsert -= THOUSANDTH_DIGIT;

        if (currentPositionForInsert <= END_MONEY_LENGTH) return money + SymbolUtil.DOLAR;

        money = money.insert(currentPositionForInsert, SymbolUtil.SPACE);
        return addSpacesToMoney(money, currentPositionForInsert);
    }

    public enum ResultMaterialDateType {FULL, SHORT}
}
