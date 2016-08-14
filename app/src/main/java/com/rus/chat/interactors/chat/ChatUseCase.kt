package com.rus.chat.interactors.chat

import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.repositories.chat.ChatRepository
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.observers.Subscribers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 23.07.2016.
 */
abstract class ChatUseCase(val chatRepository: ChatRepository) : BaseUseCase() {

    fun <T> execute(query: ChatQuery, subscriber: Subscriber<T> = Subscribers.empty())  {
        this.subscription = chatRepository.query<T>(query)
                .onBackpressureBuffer(10000)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}