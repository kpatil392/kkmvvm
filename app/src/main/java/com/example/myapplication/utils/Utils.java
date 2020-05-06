package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;


import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.annotations.NonNull;


public class Utils { /**
 * Disables all the {@link RecyclerView} animations.
 */
public static void disableAnimations(@NonNull RecyclerView recyclerView) {

    if(recyclerView.getItemAnimator() != null) {
        recyclerView.setItemAnimator(null);
    }
}




    /**
     * Sets the {@link TextView}'s left {@link Drawable}.
     */
    public static void setDrawableLeft(@NonNull TextView textView, @Nullable Drawable drawable) {

        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }




    /**
     * Retrieves the {@link Drawable} for the corresponding drawable resource id
     * and applies the specified color to it.
     *
     * @param context the context
     * @param drawableId the drawable resource id
     * @param desiredColor the desired color to be applied to the retrieved drawable
     * @return the retrieved and colored drawable or null.
     */
    @Nullable
    public static Drawable getColoredDrawable(@NonNull Context context,
                                              @DrawableRes int drawableId,
                                              @ColorInt int desiredColor) {

        final Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        return ((drawable != null) ? getColoredDrawable(drawable, desiredColor) : null);
    }




    /**
     * Applies the specified color to the specified {@link Drawable}.
     */
    @NonNull
    public static Drawable getColoredDrawable(@NonNull Drawable drawable, @ColorInt int desiredColor) {

        drawable.mutate().setColorFilter(
                desiredColor,
                PorterDuff.Mode.SRC_ATOP
        );

        return drawable;
    }




    /**
     * Creates a formatted html text from the specified raw text.
     *
     * @param rawText the text to be formatted
     * @return the formatted html text
     */
    @SuppressWarnings("NewApi")
    public static CharSequence fromHtml(CharSequence rawText) {
        if(!(rawText instanceof String)) {
            return rawText;
        }

        final String text = (String) rawText;
                  return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);

/*
        if(IS_AT_LEAST_NOUGAT) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }*/
    }


}
