package com.rus.chat.utils

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.Handle
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.repositories.session.datasource.SessionDataSource
import com.rus.chat.repositories.session.datasource.SessionDataSourceImpl
import rx.Observable

/**
 * Created by RUS on 13.07.2016.
 */
class HandleUtils {

    companion object {

        fun registerHandlers(repository: BaseRepository, dataSource: Any) {
            for(clazz in dataSource.javaClass.interfaces) {
                for(method in clazz.declaredMethods) {
                    val handle = method.getAnnotation(Handle::class.java)
                    repository.queryHandlers.put(handle.value.java, object : BaseRepository.QueryHandler {
                        override fun <T> handleQuery(query: BaseQuery): Observable<T> = method.invoke(dataSource, query) as Observable<T>
                    })
                }
            }
        }

    }

}