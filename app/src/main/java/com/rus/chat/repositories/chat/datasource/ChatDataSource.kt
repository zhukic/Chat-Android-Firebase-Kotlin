package com.rus.chat.repositories.chat.datasource

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.Handle
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.MessageResponse
import rx.Observable

/**
 * Created by RUS on 23.07.2016.
 */
interface ChatDataSource {

    @Handle(ChatQuery.GetChatMessages::class)
    fun getChatMessages(query: ChatQuery.GetChatMessages): Observable<MessageResponse.Response>

    @Handle(ChatQuery.SendMessage::class)
    fun sendMessage(query: ChatQuery.SendMessage): Observable<FirebaseResponse>

}