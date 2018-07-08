package com.freeworldone.honorpay.ui.base.extensions

import android.animation.ObjectAnimator
import android.widget.TextView
import com.freeworldone.honorpay.R

fun TextView.cycleExpansion(lines: Int) {
    val expanding = maxLines == lines
    ObjectAnimator.ofInt(this, "maxLines", if (expanding) lineCount else lines).setDuration(200).start()
    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, if (expanding) null else drawable(R.drawable.ic_expand_more_24dp))
}