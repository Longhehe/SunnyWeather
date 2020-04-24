package com.example.sunnyweather.Tool

import android.view.View
import android.widget.Toast
import com.example.sunnyweather.SunnyWeatherApplication
import com.google.android.material.snackbar.Snackbar

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(SunnyWeatherApplication.context, this, duration).show()
}

fun Int.showToast( duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(SunnyWeatherApplication.context, this, duration).show()
}

fun View.showSnakebar(
    text: String, actionText: String? = null, duration: Int = Snackbar.LENGTH_SHORT
    , block: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}

fun View.showSnakebar(
    resId: Int, actionText: String? = null, duration: Int = Snackbar.LENGTH_SHORT
    , block: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, resId, duration)
    if (actionText != null && block != null) {
        snackbar.setAction(actionText){
            block()
        }
    }
    snackbar.show()
}