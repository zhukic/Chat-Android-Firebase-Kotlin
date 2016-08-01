package com.rus.chat.repositories.chat

import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.chat.datasource.ChatDataSourceImpl
import com.rus.chat.utils.HandleUtils
import rx.Observable
import javax.inject.Inject

/**
 * Created by RUS on 23.07.2016.
 */
class ChatRepository @Inject constructor(dataSourceImpl: ChatDataSourceImpl) : BaseRepository() {

    init {
        HandleUtils.registerHandlers(this, dataSourceImpl)
    }

    fun <T> query(baseQuery: BaseQuery): Observable<T> = getObservable(baseQuery)
}