package com.rus.chat.ui

/**
 * Created by RUS on 22.07.2016.
 */
interface BaseView {

    fun onError(throwable: Throwable)

    fun showToast(message: String)

    fun showSnackbar(message: String)

}