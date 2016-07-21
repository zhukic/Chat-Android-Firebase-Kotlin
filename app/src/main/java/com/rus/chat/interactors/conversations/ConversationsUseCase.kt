package com.rus.chat.interactors.conversations

import com.rus.chat.App
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversation.ConversationsQuery
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.login.SessionRepository
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.observers.Subscribers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsUseCase : BaseUseCase() {

    init {
        App.conversationsComponent.inject(this)
    }

    fun <T> execute(query: ConversationsQuery.Query, subscriber: Subscriber<T> = Subscribers.empty()) = super.execute(query, subscriber)
}