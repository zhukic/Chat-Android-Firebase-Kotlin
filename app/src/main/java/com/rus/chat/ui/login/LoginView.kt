package com.rus.chat.ui.login

import com.rus.chat.entity.session.User
import com.rus.chat.ui.BaseView

/**
 * Created by RUS on 11.07.2016.
 */
interface LoginView : BaseView {

    fun openConversationsActivity(uid: String)

    fun showSignInProgress()

    fun hideSignInProgress()

    fun showRegisterProgress()

    fun hideRegisterProgress()

}