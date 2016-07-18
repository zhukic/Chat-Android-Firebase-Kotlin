package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.session.Query
import com.rus.chat.entity.session.SessionQuery
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

    private var subscription: Subscription = Subscriptions.empty()

    init {
        App.sessionComponent.inject(this)
    }

    abstract fun <T> execute(query: Query, subscriber: Subscriber<T> = Subscribers.empty())

    fun unsubscribe() = subscription.unsubscribe()
}