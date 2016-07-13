package com.rus.chat.ui.login

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginView {

    fun openConversationsActivity()

    fun onLoginError(t: Throwable)

    fun showToast(message: String)

    fun showSignInProgress()

    fun hideSignInProgress()

    fun showRegisterProgress()

    fun hideRegisterProgress()

}