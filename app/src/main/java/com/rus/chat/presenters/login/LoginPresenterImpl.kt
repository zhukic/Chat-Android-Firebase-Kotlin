package com.rus.chat.presenters.login

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.interactors.session.*
import com.rus.chat.ui.login.LoginView
import com.rus.chat.utils.Logger
import rx.Subscriber
import rx.Subscription
import rx.observers.Subscribers
import rx.subscriptions.Subscriptions

/**
 * Created by RUS on 10.07.2016.
 */
class LoginPresenterImpl(var loginView: LoginView?) : LoginPresenter {

    lateinit var useCase: SessionUseCase

    override fun initialize() {
        useCase = GetCurrentUser()
        useCase.execute(SessionQuery.GetCurrentUser(), UserSubscriber())
    }

    override fun signIn(email: String, password: String) {
        loginView?.showSignInProgress()
        useCase = SignIn()
        useCase.execute(SessionQuery.SignIn(email, password), SignInSubscriber())
    }

    override fun register(email: String, name: String, password: String) {
        loginView?.showRegisterProgress()
        useCase = Register()
        useCase.execute(SessionQuery.Register(email, name, password), RegisterSubscriber())
    }

    override fun signOut() {
        useCase = SignOut()
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
            if(firebaseUser != null) loginView?.openConversationsActivity()
        }

    }

    private inner class SignInSubscriber : Subscriber<FirebaseUser>() {

        override fun onNext(firebaseUser: FirebaseUser?) {}

        override fun onError(e: Throwable) {
            loginView?.hideSignInProgress()
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.hideSignInProgress()
            loginView?.openConversationsActivity()
        }
    }

    private inner class RegisterSubscriber : Subscriber<FirebaseUser>() {

        override fun onNext(firebaseUser: FirebaseUser) {
            useCase = AddUserToDb()
            useCase.execute(SessionQuery.AddToDb(firebaseUser.uid, "zhukic"), AddUserToDbSubscriber())
        }

        override fun onError(e: Throwable) {
            loginView?.hideRegisterProgress()
            loginView?.onLoginError(e)
        }

        override fun onCompleted() { }
    }

    private inner class AddUserToDbSubscriber : Subscriber<User>() {

        override fun onError(e: Throwable) {
            loginView?.onLoginError(e)
        }

        override fun onCompleted() {
            loginView?.hideRegisterProgress()
            loginView?.openConversationsActivity()
        }

        override fun onNext(t: User?) {}

    }

    private inner class SignOutSubscriber : Subscriber<FirebaseUser>() {
        override fun onNext(t: FirebaseUser?) {
        }

        override fun onError(e: Throwable?) {
        }

        override fun onCompleted() {
        }

    }

    companion object {
        val COMPLETED_REGISTRATION: String = "Registration has been completed"
    }
}