package com.rus.chat.repositories.login

import com.rus.chat.entity.session.Query
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.interactors.session.Handle
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.utils.Logger
import rx.Observable
import rx.Subscriber
import java.lang.reflect.Method
import java.util.*
import kotlin.reflect.KClass

/**
 * Created by RUS on 11.07.2016.
 */
class SessionRepository {

    lateinit private var queryHandlers: HashMap<KClass<out Query>, SessionRepository.QueryHandler>

    fun query(query: Query): Observable<Unit> = createObservable(query)

    private fun registerHandlers(dataSource: Any) {
        for(clazz in dataSource.javaClass.interfaces) {
            for(method in clazz.declaredMethods) {
                val handle = method.getAnnotation(Handle::class.java)
                queryHandlers.put(handle.value, object : SessionRepository.QueryHandler {
                    override fun handleQuery(query: Query): Observable<Unit> {
                        return method.invoke(dataSource, query) as Observable<Unit>
                    }
                })
            }
        }
    }

    private fun createObservable(query: Query): Observable<Unit> {
        for(clazz in SessionDataSourceImpl().javaClass.interfaces) {
            for(method in clazz.declaredMethods) {
                val handle = method.getAnnotation(Handle::class.java)
                if(query.javaClass.equals(handle.value))
                    return method.invoke(SessionDataSourceImpl(), query) as Observable<Unit>
            }
        }
        throw IllegalArgumentException()
    }

    private interface QueryHandler {
        fun handleQuery(query: Query) : Observable<Unit>
    }

}