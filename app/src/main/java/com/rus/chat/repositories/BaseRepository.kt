package com.rus.chat.repositories

import com.google.gson.annotations.Expose
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.repositories.conversations.ConversationsRepository
import rx.Observable
import java.util.*

/**
 * Created by RUS on 19.07.2016.
 */
abstract class BaseRepository {

    var queryHandlers: HashMap<Class<out BaseQuery>, BaseRepository.QueryHandler> = HashMap()

    protected fun <T> getObservable(query: BaseQuery): Observable<T> = queryHandlers[query.javaClass]?.handleQuery(query)
            ?: throw IllegalArgumentException("No handler is registered for query ${query.javaClass}")

    interface QueryHandler {
        fun <T> handleQuery(query: BaseQuery): Observable<T>
    }
}