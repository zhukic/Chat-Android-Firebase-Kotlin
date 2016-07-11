package com.rus.chat.presenters.login

import com.rus.chat.interactors.DoLoginUseCase
import com.rus.chat.ui.login.LoginView
import com.rus.chat.utils.Logger
import rx.Subscriber

/**
 * Created by RUS on 10.07.2016.
 */
class LoginPresenterImpl(val loginView: LoginView?) : LoginPresenter {

    lateinit var doLoginUseCase: DoLoginUseCase

    init {
        doLoginUseCase = DoLoginUseCase()
    }

    override fun doLogin(email: String, password: String) {
        doLoginUseCase.doLogin(email, password, LoginSubscriber())
    }

    private inner class LoginSubscriber : Subscriber<Any>() {

        override fun onCompleted() {
            loginView?.openConversationsActivity()
        }

        override fun onError(e: Throwable) {
            loginView?.onLoginError(e)
        }

        override fun onNext(t: Any?) {}
    }
}