package com.android.manthan.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun View.isVisible(visible: Boolean, invisibleOrGone: Int = View.GONE) {
    this.visibility = if (visible) View.VISIBLE else invisibleOrGone
}

fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))




