package com.rus.chat.interactors.chat

import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.chat.ChatRepository
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by RUS on 01.08.2016.
 */
@UseCase
class SendMessage @Inject constructor(chatRepository: ChatRepository) : ChatUseCase(chatRepository) {

    fun execute(conversationId: String, text: String, time: String, subscriber: Subscriber<FirebaseResponse>) = super.execute(ChatQuery.SendMessage(conversationId, text, time), subscriber)
}