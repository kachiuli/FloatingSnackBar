package com.ankushyerwar.floatingsnackbar.customization;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ankushyerwar.floatingsnackbar.R;
import com.ankushyerwar.floatingsnackbar.definations.ColorInt;
import com.ankushyerwar.floatingsnackbar.definations.DrawableRes;
import com.ankushyerwar.floatingsnackbar.definations.Duration;
import com.ankushyerwar.floatingsnackbar.definations.StringRes;
import com.ankushyerwar.floatingsnackbar.utils.Type;
import com.google.android.material.snackbar.Snackbar;

import static com.ankushyerwar.floatingsnackbar.utils.Constants.corner;
import static com.ankushyerwar.floatingsnackbar.utils.Constants.margin;
import static com.ankushyerwar.floatingsnackbar.utils.Constants.maxLines;
import static com.ankushyerwar.floatingsnackbar.utils.Constants.noVal;
import static com.ankushyerwar.floatingsnackbar.utils.Constants.textSize;

/* Last Updated: 04\12\2019
*  Author: Ankush Yerawar
*/

public class Customize {

    public static Snackbar defaultRes(@NonNull View view, @StringRes int resId, @Duration int length,
                                      Type type, boolean withIcon) {

        Snackbar snackbar = Snackbar.make(view, resId, length);

        return modify(snackbar, type, withIcon);
    }

    public static Snackbar defaultChar(@NonNull View view, CharSequence text, @Duration int length,
                                       Type type, boolean withIcon) {

        Snackbar snackbar = Snackbar.make(view, text, length);

        return modify(snackbar, type, withIcon);
    }

    public static Snackbar customIcon(@NonNull View view, CharSequence text, @Duration int length,
                                      Type type, @DrawableRes int iconId) {

        Snackbar snackbar = Snackbar.make(view, text, length);

        return modifyIcon(snackbar,type,iconId);
    }

    public static Snackbar customIcon(@NonNull View view, @StringRes int resId, @Duration int length,
                                      Type type, @DrawableRes int iconId) {

        Snackbar snackbar = Snackbar.make(view, resId, length);

        return modifyIcon(snackbar,type,iconId);
    }

    public static Snackbar customChar(@DrawableRes int iconId, @NonNull View view, CharSequence text,
                                      @Duration int length, @ColorInt int backgroundColor,
                                      @ColorInt int textColor) {

        Snackbar snackbar = Snackbar.make(view, text, length);

        return customModify(snackbar, iconId, textColor, backgroundColor);
    }

    public static Snackbar customRes(@DrawableRes int iconId, @NonNull View view, @StringRes int resId,
                                     @Duration int length, @ColorInt int backgroundColor,
                                     @ColorInt int textColor) {

        Snackbar snackbar = Snackbar.make(view, resId, length);

        return customModify(snackbar, iconId, textColor, backgroundColor);
    }

    private static Snackbar modify(Snackbar snackbar, Type type, boolean withIcon) {
        try {

            final View snackBarView = snackbar.getView();

            setTextStyle(snackBarView, type.getIcon(), withIcon);

            snackBarView.setLayoutParams(setMargins(snackBarView));

            snackBarView.setBackground(snackBarView.getContext().getDrawable(type.getBackground()));

            return snackbar;

        } catch (NullPointerException | ClassCastException ignored) {
        }
        return snackbar;
    }

    private static Snackbar modifyIcon(Snackbar snackbar, Type type, @DrawableRes int iconId) {
        try {

            final View snackBarView = snackbar.getView();

            setTextStyle(snackBarView, iconId, true);

            snackBarView.setLayoutParams(setMargins(snackBarView));

            snackBarView.setBackground(snackBarView.getContext().getDrawable(type.getBackground()));

            return snackbar;

        } catch (NullPointerException | ClassCastException ignored) {
        }
        return snackbar;
    }

    private static Snackbar customModify(Snackbar snackbar, @DrawableRes int iconId, @ColorInt int textColor,
                                         @ColorInt int backgroundColor) {
        try {

            final View snackBarView = snackbar.getView();

            setCustomTextStyle(snackBarView, iconId, textColor);

            snackBarView.setLayoutParams(setMargins(snackBarView));

            snackBarView.setBackground(setBackground(backgroundColor));

            return snackbar;

        } catch (NullPointerException | ClassCastException ignored) {
        }
        return snackbar;
    }

    private static void setTextStyle(View view, @DrawableRes int resId, boolean withIcon) {
        TextView textView = view.findViewById(R.id.snackbar_text);
        textView.setTextSize(textSize);
        textView.setMaxLines(maxLines);
        if (withIcon) {
            textView.setCompoundDrawablesWithIntrinsicBounds(resId, noVal, noVal, noVal);
            textView.setCompoundDrawablePadding(textView.getResources().getDimensionPixelOffset(R.dimen.icon_padding));
        }
    }

    private static void setCustomTextStyle(View view, @DrawableRes int resId, @ColorInt int textColor) {
        TextView textView = view.findViewById(R.id.snackbar_text);
        textView.setTextSize(textSize);
        textView.setMaxLines(maxLines);
        textView.setTextColor(textColor);
        textView.setCompoundDrawablesWithIntrinsicBounds(resId, noVal, noVal, noVal);
        textView.setCompoundDrawablePadding(textView.getResources().getDimensionPixelOffset(R.dimen.icon_padding));
    }

    private static ViewGroup.MarginLayoutParams setMargins(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)
                view.getLayoutParams();

        marginLayoutParams.setMargins(marginLayoutParams.leftMargin + margin,
                marginLayoutParams.topMargin, marginLayoutParams.rightMargin + margin,
                marginLayoutParams.bottomMargin + margin);

        return marginLayoutParams;
    }

    private static GradientDrawable setBackground(int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(corner);
        shape.setColor(backgroundColor);
        return shape;
    }
}
