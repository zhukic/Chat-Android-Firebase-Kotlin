package com.rus.chat.repositories.conversations.datasource

import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.repositories.conversations.datasource.mapper.ConversationMapper
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.Handle
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.entity.response.FirebaseResponse
import rx.Observable

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsDataSource {

    @Handle(ConversationsQuery.GetConversations::class)
    fun initialize(query: ConversationsQuery.GetConversations): Observable<ConversationResponse>

    @Handle(ConversationsQuery.CreateConversation::class)
    fun createConversation(query: ConversationsQuery.CreateConversation): Observable<FirebaseResponse>

}