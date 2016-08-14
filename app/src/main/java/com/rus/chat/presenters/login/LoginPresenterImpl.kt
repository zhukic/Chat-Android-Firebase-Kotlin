package com.rus.chat.presenters.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.interactors.session.*
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.ui.login.LoginView
import com.rus.chat.utils.Logger
import rx.Subscriber
import rx.Subscription
import rx.observers.Subscribers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 10.07.2016.
 */

class LoginPresenterImpl @Inject constructor(val signIn: SignIn,
                                             val signOut: SignOut,
                                             val register: Register) : LoginPresenter {

    var loginView: LoginView? = null

    override fun attachView(loginView: LoginView) {
        this.loginView = loginView
    }

    override fun signIn(name: String, password: String) {
        loginView?.showSignInProgress()
        val email = name + "@gmail.com"
        signIn.execute(email, password, SignInSubscriber())
    }

    override fun register(name: String, password: String) {
        loginView?.showRegisterProgress()
        val email = name + "@gmail.com"
        register.execute(email, name, password, RegisterSubscriber())
    }

    override fun signOut() {
        signOut.execute(SignOutSubscriber())
    }

    override fun onDestroy() {
        this.loginView = null
//        this.useCase.unsubscribe()
    }

    private inner class SignInSubscriber : Subscriber<User>() {

        override fun onNext(user: User) {
            loginView?.openConversationsActivity(user.uid)
        }

        override fun onError(e: Throwable) {
            loginView?.hideSignInProgress()
            loginView?.onError(e)
        }

        override fun onCompleted() {
            loginView?.hideSignInProgress()
        }
    }

    private inner class RegisterSubscriber : Subscriber<User>() {

        override fun onNext(user: User) {
            loginView?.openConversationsActivity(user.uid)
        }

        override fun onError(e: Throwable) {
            loginView?.hideRegisterProgress()
            loginView?.onError(e)
        }

        override fun onCompleted() {
            loginView?.hideRegisterProgress()
        }
    }

    private inner class SignOutSubscriber : Subscriber<User>() {
        override fun onNext(user: User?) {}

        override fun onError(throwable: Throwable) {
            loginView?.onError(throwable)
        }

        override fun onCompleted() {}

    }

    companion object {
        val COMPLETED_REGISTRATION: String = "Registration has been completed"
    }
}