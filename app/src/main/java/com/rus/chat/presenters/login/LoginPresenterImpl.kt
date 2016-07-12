package com.rus.chat.presenters.login

import com.rus.chat.interactors.session.Register
import com.rus.chat.interactors.session.SignIn
import com.rus.chat.ui.login.LoginView
import com.rus.chat.utils.Logger
import rx.Subscriber

/**
 * Created by RUS on 10.07.2016.
 */
class LoginPresenterImpl(var loginView: LoginView?) : LoginPresenter {

    lateinit var signIn: SignIn
    lateinit var register: Register

    init {
        signIn = SignIn()
        register = Register()
    }

    override fun signIn(email: String, password: String) {
        signIn.execute(email, password, SignInSubscriber())
    }

    override fun register(email: String, password: String) {
        register.execute(email, password, RegisterSubscriber())
    }

    override fun onDestroy() {
        this.loginView = null
    }

    private inner class SignInSubscriber : Subscriber<Unit>() {

        override fun onNext(t: Unit?) {}

        override fun onError(e: Throwable) {
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.openConversationsActivity()
        }
    }

    private inner class RegisterSubscriber : Subscriber<Unit>() {

        override fun onNext(t: Unit?) {}

        override fun onError(e: Throwable) {
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.showToast(COMPLETED_REGISTRATION)
        }
    }

    companion object {

        val COMPLETED_REGISTRATION: String = "Registration has been completed"
    }
}