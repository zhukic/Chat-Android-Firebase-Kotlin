package com.rus.chat.repositories.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.entity.query.Handle
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.datasource.SessionDataSourceImpl
import com.rus.chat.utils.HandleUtils
import com.rus.chat.utils.Logger
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
class SessionRepository(sessionDataSourceImpl: SessionDataSourceImpl) : BaseRepository() {

    init {
        HandleUtils.registerHandlers(this, sessionDataSourceImpl)
    }

    fun <T> query(sessionQuery: SessionQuery): Observable<T> = getObservable(sessionQuery)

}