package com.rus.chat.interactors.conversations

import com.rus.chat.App
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.session.SessionRepository
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.observers.Subscribers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
abstract class ConversationsUseCase(val conversationsRepository: ConversationsRepository) : BaseUseCase() {

    protected fun <T> execute(query: ConversationsQuery, subscriber: Subscriber<T> = Subscribers.empty()) {
        this.subscription = conversationsRepository.query<T>(query)
                .onBackpressureBuffer(10000)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }
}