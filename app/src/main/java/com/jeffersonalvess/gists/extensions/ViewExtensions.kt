package com.jeffersonalvess.gists.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.loadingVisibility(showShow: Boolean) {
    visibility = if (showShow) VISIBLE else GONE
}