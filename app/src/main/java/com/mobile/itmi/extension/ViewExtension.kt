package com.mobile.itmi.extension

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mobile.itmi.R

fun TextView.setStatus(status: String) {
    when (status) {
        "Active" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_rounded_green)
            text = status
        }
        "In Review" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_rounded_yellow)
            text = status
        }
        "Prescription Rejected" -> {
            background = ContextCompat.getDrawable(context, R.drawable.bg_rounded_red)
            text = status
        }
    }
}