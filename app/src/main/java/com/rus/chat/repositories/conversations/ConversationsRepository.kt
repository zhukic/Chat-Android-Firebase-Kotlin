package com.rus.chat.repositories.conversations

import com.rus.chat.entity.conversation.User
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSource
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import rx.Observable

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsRepository {

    fun query(): Observable<List<User>> = ConversationsDataSourceImpl().getConversations()

}