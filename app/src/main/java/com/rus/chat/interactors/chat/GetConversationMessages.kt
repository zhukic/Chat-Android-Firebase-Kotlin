package com.rus.chat.interactors.chat

import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.entity.response.ResponseType
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.chat.ChatRepository
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by RUS on 01.08.2016.
 */
@UseCase
class GetConversationMessages @Inject constructor(chatRepository: ChatRepository) : ChatUseCase(chatRepository) {

    fun execute(conversationId: String, subscriber: Subscriber<Pair<MessageModel, ResponseType>>)
            = super.execute(ChatQuery.GetConversationMessages(conversationId), subscriber)

}