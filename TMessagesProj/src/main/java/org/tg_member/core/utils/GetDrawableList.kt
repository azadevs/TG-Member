package org.tg_member.core.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.telegram.ui.ActionBar.Theme

fun getDrawableStateList(
    @DrawableRes drawable: Int,
    context: Context,
    color: Int
): StateListDrawable {
    val stateListDrawablePopup = ContextCompat.getDrawable(
        context, drawable
    ) as StateListDrawable
    val gradientDrawablePopup = stateListDrawablePopup.current as GradientDrawable
    gradientDrawablePopup.setColor(Theme.getColor(color))
    return stateListDrawablePopup
}
