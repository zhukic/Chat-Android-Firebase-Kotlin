package com.rus.chat.presenters.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.interactors.session.*
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.login.SessionRepository
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

class LoginPresenterImpl @Inject constructor(val useCase: SessionUseCase) : LoginPresenter {

    var loginView: LoginView? = null

    override fun attachView(loginView: LoginView) {
        this.loginView = loginView
    }

    override fun initialize() {
        useCase.execute(SessionQuery.GetCurrentUser(), UserSubscriber())
    }

    override fun signIn(email: String, password: String) {
        loginView?.showSignInProgress()
        useCase.execute(SessionQuery.SignIn(email, password), SignInSubscriber())
    }

    override fun register(email: String, name: String, password: String) {
        loginView?.showRegisterProgress()
        useCase.execute(SessionQuery.Register(email, name, password), RegisterSubscriber())
    }

    override fun signOut() {
        useCase.execute(SessionQuery.SignOut(), SignOutSubscriber())
    }

    override fun onDestroy() {
        this.loginView = null
        this.useCase.unsubscribe()
    }

    private inner class UserSubscriber : Subscriber<FirebaseUser>() {

        override fun onError(e: Throwable?) {
        }

        override fun onCompleted() {
        }

        override fun onNext(firebaseUser: FirebaseUser?) {
            if(firebaseUser != null) loginView?.openConversationsActivity(firebaseUser.uid)
        }

    }

    private inner class SignInSubscriber : Subscriber<FirebaseUser>() {

        override fun onNext(firebaseUser: FirebaseUser) {
            loginView?.openConversationsActivity(firebaseUser.uid)
        }

        override fun onError(e: Throwable) {
            loginView?.hideSignInProgress()
            loginView?.onError(e)
        }

        override fun onCompleted() {
            loginView?.hideSignInProgress()
        }
    }

    private inner class RegisterSubscriber : Subscriber<FirebaseUser>() {

        override fun onNext(firebaseUser: FirebaseUser) {
            loginView?.openConversationsActivity(firebaseUser.uid)
        }

        override fun onError(e: Throwable) {
            loginView?.hideRegisterProgress()
            loginView?.onError(e)
        }

        override fun onCompleted() {
            loginView?.hideRegisterProgress()
        }
    }

    private inner class SignOutSubscriber : Subscriber<FirebaseUser>() {
        override fun onNext(t: FirebaseUser?) {}

        override fun onError(e: Throwable?) {}

        override fun onCompleted() {}

    }

    companion object {
        val COMPLETED_REGISTRATION: String = "Registration has been completed"
    }
}