package com.rus.chat.repositories.login.datasource

import rx.Observable

/**
 * Created by RUS on 11.07.2016.
 */
interface ISessionDataSource {

    fun register(email: String, password: String): Observable<Any>

    fun signIn(email: String, password: String): Observable<Any>

    fun signOut(): Observable<Any>

}