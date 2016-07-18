package com.rus.chat.repositories.conversations.datasource

import com.rus.chat.entity.conversation.User
import rx.Observable

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsDataSource {

    fun getConversations(): Observable<User>

}