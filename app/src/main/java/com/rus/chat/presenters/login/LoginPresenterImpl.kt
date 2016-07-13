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
        loginView?.showSignInProgress()
        signIn.execute(email, password, SignInSubscriber())
    }

    override fun register(email: String, password: String) {
        loginView?.showRegisterProgress()
        register.execute(email, password, RegisterSubscriber())
    }

    override fun onDestroy() {
        this.loginView = null
    }

    private inner class SignInSubscriber : Subscriber<Unit>() {

        override fun onNext(t: Unit?) {}

        override fun onError(e: Throwable) {
            loginView?.hideSignInProgress()
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.hideSignInProgress()
            loginView?.openConversationsActivity()
        }
    }

    private inner class RegisterSubscriber : Subscriber<Unit>() {

        override fun onNext(t: Unit?) {}

        override fun onError(e: Throwable) {
            loginView?.hideRegisterProgress()
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.hideRegisterProgress()
            loginView?.showToast(COMPLETED_REGISTRATION)
        }
    }

    companion object {

        val COMPLETED_REGISTRATION: String = "Registration has been completed"
    }
}