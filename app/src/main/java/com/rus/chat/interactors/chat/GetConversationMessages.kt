package com.rus.chat.interactors.chat

import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.MessageResponse
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.chat.ChatRepository
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by RUS on 01.08.2016.
 */
@UseCase
class GetConversationMessages @Inject constructor(chatRepository: ChatRepository) : ChatUseCase(chatRepository) {

    fun execute(conversationId: String, subscriber: Subscriber<MessageResponse.Response>) = super.execute(ChatQuery.GetConversationMessages(conversationId), subscriber)

}