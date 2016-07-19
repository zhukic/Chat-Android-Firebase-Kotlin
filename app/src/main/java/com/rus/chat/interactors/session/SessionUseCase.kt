package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.repositories.login.SessionRepository
import rx.Subscriber
import rx.Subscription
import rx.observers.Subscribers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 14.07.2016.
 */
class SessionUseCase : BaseUseCase() {

    init {
        App.sessionComponent.inject(this)
    }

    fun <T> execute(query: SessionQuery.Query, subscriber: Subscriber<T>) = super.execute(query, subscriber)
}