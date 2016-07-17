package com.rus.chat.interactors.conversations

import com.rus.chat.entity.conversation.User
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.conversations.ConversationsRepository
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.subscriptions.Subscriptions

/**
 * Created by RUS on 17.07.2016.
 */
abstract class ConversationUseCase {

    lateinit var conversationRepository: ConversationsRepository

    private var subscription: Subscription = Subscriptions.empty()

    init {
        conversationRepository = ConversationsRepository()
    }

    abstract fun execute(subscriber: Subscriber<List<User>>)

    fun unsubscribe() = subscription.unsubscribe()

}