package com.devdefiance.android.query.utils;

import android.os.Build;

/**
 * Created by darth on 1/6/18.
 */

public class BasicUtils {

    public static boolean canUseWindowsNoLimit() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
