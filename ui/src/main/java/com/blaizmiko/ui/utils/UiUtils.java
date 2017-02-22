package com.blaizmiko.ui.utils;

import android.content.Context;
import android.util.TypedValue;

public final class UiUtils {

    public static int dpToPx (final Context context, final int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
