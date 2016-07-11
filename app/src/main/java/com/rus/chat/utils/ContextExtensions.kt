package com.rus.chat.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.widget.Toast

/**
 * Created by RUS on 11.07.2016.
 */

fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_LONG) = Toast.makeText(this, message, length).show()