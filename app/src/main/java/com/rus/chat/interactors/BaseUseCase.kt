package com.rus.chat.interactors

import com.rus.chat.App
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.repositories.BaseRepository
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.observers.Subscribers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 20.07.2016.
 */
abstract class BaseUseCase {

    protected var subscription: Subscription = Subscriptions.empty()

    fun unsubscribe() = subscription.unsubscribe()
}