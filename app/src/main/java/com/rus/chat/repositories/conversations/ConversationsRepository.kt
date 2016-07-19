package com.rus.chat.repositories.conversations

import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSource
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.login.SessionRepository
import rx.Observable
import java.util.*
import javax.inject.Singleton

/**
 * Created by RUS on 17.07.2016.
 */
@Singleton
class ConversationsRepository : BaseRepository() {

    override fun <T> query(baseQuery: BaseQuery): Observable<T> = getObservable(baseQuery)

}