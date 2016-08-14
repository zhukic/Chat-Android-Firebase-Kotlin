package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.entity.session.User
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.SessionRepository
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.observers.Subscribers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 14.07.2016.
 */
abstract class SessionUseCase (val sessionRepository: SessionRepository) : BaseUseCase(){

    protected fun <T> execute(query: SessionQuery, subscriber: Subscriber<T>)  {
        this.subscription = sessionRepository.query<T>(query)
                .onBackpressureBuffer(10000)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }
}