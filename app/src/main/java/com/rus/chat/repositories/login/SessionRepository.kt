package com.rus.chat.repositories.login

import com.rus.chat.repositories.login.datasource.SessionDataSource
import rx.Observable
import rx.Subscriber
import java.util.*

/**
 * Created by RUS on 11.07.2016.
 */
class SessionRepository {

    fun signInQuery(email: String, password: String): Observable<Any>
            = SessionDataSource().signIn(email, password)

}