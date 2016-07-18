package com.rus.chat.ui.login

import com.rus.chat.entity.conversation.User

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginView {

    fun openConversationsActivity(uid: String)

    fun onLoginError(t: Throwable)

    fun showToast(message: String)

    fun showSignInProgress()

    fun hideSignInProgress()

    fun showRegisterProgress()

    fun hideRegisterProgress()

}