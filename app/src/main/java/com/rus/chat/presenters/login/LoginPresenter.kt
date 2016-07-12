package com.rus.chat.presenters.login

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginPresenter {

    fun signIn(email: String, password: String)

    fun register(email: String, password: String)

    fun onDestroy()

}