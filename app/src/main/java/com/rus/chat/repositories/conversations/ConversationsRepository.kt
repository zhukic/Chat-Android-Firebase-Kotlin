package com.rus.chat.repositories.conversations

import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSource
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.utils.HandleUtils
import rx.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by RUS on 17.07.2016.
 */
@Singleton
class ConversationsRepository @Inject constructor(conversationsDataSourceImpl: ConversationsDataSourceImpl) : BaseRepository() {

    init {
        HandleUtils.registerHandlers(this, conversationsDataSourceImpl)
    }

    fun <T> query(conversationsQuery: ConversationsQuery): Observable<T> = getObservable(conversationsQuery)

}