package com.rus.chat.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by RUS on 11.07.2016.
 */
fun View.showSnackBar(message: CharSequence = "Error", length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
}