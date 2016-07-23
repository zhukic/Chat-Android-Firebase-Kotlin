package com.rus.chat.presenters.login

import com.rus.chat.presenters.BasePresenter
import com.rus.chat.ui.login.LoginView

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginPresenter : BasePresenter {

    fun attachView(loginView: LoginView)

    fun initialize()

    fun signIn(email: String, password: String)

    fun register(email: String, name: String, password: String)

    fun signOut()

}