package com.devdefiance.android.query.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.devdefiance.android.query.R;

/**
 * Created by darth on 1/6/18.
 */

public class BasicUtils {

    public static boolean canUseWindowsNoLimit() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static void closeInputMethod(Context context, View focus) throws NullPointerException {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(focus.getWindowToken(), 0);
    }

    public static boolean isLargeScreen(FragmentActivity fragmentActivity) {
        try {
            return fragmentActivity.getResources().getBoolean(R.bool.isLargeScreen);
        } catch (NullPointerException npe) {
            return false;
        }
    }

    public static void dismissView(final View toDismiss, int duration) {
        toDismiss
                .animate().x(-toDismiss.getMeasuredWidth())
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        toDismiss.setVisibility(View.GONE);
                    }
                }).start();
    }
}
