package com.rus.chat.interactors.conversations

import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.conversations.ConversationsRepository
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by RUS on 01.08.2016.
 */
@UseCase
class CreateConversation @Inject constructor(conversationsRepository: ConversationsRepository) : ConversationsUseCase(conversationsRepository) {

    fun execute(conversationName: String, subscriber: Subscriber<FirebaseResponse>)
            = super.execute(ConversationsQuery.CreateConversation(conversationName), subscriber)

}