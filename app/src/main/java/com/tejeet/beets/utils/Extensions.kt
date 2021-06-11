package com.tejeet.beets.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.app.tiktok.utils.Utility

fun AppCompatTextView.setTextOrHide(value: String? = null) {
    if (!value.isNullOrBlank()) {
        text = value
        isVisible = true
    } else {
        isVisible = false
    }
}

fun <T>Class<in T>.logError(message: String) {
    Log.e(this::class.java.name, message)
}

fun Any.logError(message: String) {
    Log.e(this::class.java.name, message)
}

fun Long.formatNumberAsReadableFormat(): String {
    return Utility.formatNumberAsNumberFormat(this)
}