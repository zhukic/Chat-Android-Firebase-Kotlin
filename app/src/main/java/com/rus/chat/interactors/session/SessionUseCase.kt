package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.repositories.login.SessionRepository
import rx.Subscriber
import rx.Subscription
import rx.observers.Subscribers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 14.07.2016.
 */
abstract class SessionUseCase {

    @Inject
    lateinit var sessionRepository: SessionRepository

    init {
        App.sessionComponent.inject(this)
    }

    private var subscription: Subscription = Subscriptions.empty()

    abstract fun execute(email: String = "", password: String = "", subscriber: Subscriber<FirebaseUser> = Subscribers.empty())

    fun unsubscribe() = subscription.unsubscribe()
}