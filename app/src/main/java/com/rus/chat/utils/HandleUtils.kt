package com.rus.chat.utils

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.session.Query
import com.rus.chat.entity.session.Handle
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSource
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import rx.Observable

/**
 * Created by RUS on 13.07.2016.
 */
class HandleUtils {

    companion object {

        fun registerHandlers(repository: SessionRepository, dataSource: SessionDataSourceImpl) {
            for(clazz in dataSource.javaClass.interfaces) {
                for(method in clazz.declaredMethods) {
                    val handle = method.getAnnotation(Handle::class.java)
                    repository.queryHandlers.put(handle.value.java, object : SessionRepository.QueryHandler {
                        override fun <T> handleQuery(query: Query): Observable<T> = method.invoke(dataSource, query) as Observable<T>
                    })
                }
            }
        }

    }

}