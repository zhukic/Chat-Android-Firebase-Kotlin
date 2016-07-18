package com.rus.chat.repositories.login

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.session.Query
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.entity.session.Handle
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.utils.Logger
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.Observable
import rx.Subscriber
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by RUS on 11.07.2016.
 */
@Singleton
class SessionRepository() {

    lateinit var queryHandlers: HashMap<Class<out Query>, SessionRepository.QueryHandler>

    init {
       queryHandlers = HashMap()
    }

    fun <T> query(query: Query): Observable<T> = queryHandlers[query.javaClass]?.handleQuery(query) ?: throw IllegalArgumentException("No handler is registered for query ${query.javaClass}")

    interface QueryHandler {
        fun <T> handleQuery(query: Query): Observable<T>
    }

}