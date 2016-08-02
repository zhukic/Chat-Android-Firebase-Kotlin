package com.rus.chat.repositories.chat.datasource

import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.entity.response.ResponseType
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.Handle
import com.rus.chat.entity.query.chat.ChatQuery
import rx.Observable

/**
 * Created by RUS on 23.07.2016.
 */
interface ChatDataSource {

    @Handle(ChatQuery.GetConversationMessages::class)
    fun getChatMessages(query: ChatQuery.GetConversationMessages): Observable<Pair<MessageModel, ResponseType>>

    @Handle(ChatQuery.SendMessage::class)
    fun sendMessage(query: ChatQuery.SendMessage): Observable<FirebaseResponse>

}