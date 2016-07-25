package com.rus.chat.interactors.chat

import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.interactors.BaseUseCase
import com.rus.chat.repositories.chat.ChatRepository
import rx.Subscriber
import rx.observers.Subscribers

/**
 * Created by RUS on 23.07.2016.
 */
class ChatUseCase(chatRepository: ChatRepository) : BaseUseCase(chatRepository) {

    fun <T> execute(query: ChatQuery.Query, subscriber: Subscriber<T> = Subscribers.empty()) = super.execute(query, subscriber)

}