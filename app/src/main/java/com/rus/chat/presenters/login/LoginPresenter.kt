package com.rus.chat.presenters.login

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginPresenter {

    fun initialize()

    fun signIn(email: String, password: String)

    fun register(email: String, name: String, password: String)

    fun signOut()

    fun onDestroy()

}