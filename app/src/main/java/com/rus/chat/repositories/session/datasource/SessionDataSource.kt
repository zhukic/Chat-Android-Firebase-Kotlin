package com.rus.chat.repositories.session.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.entity.query.Handle
import rx.Observable

/**
 * Created by RUS on 11.07.2016.
 */
interface SessionDataSource {

    @Handle(SessionQuery.GetCurrentUser::class)
    fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<User>

    @Handle(SessionQuery.Register::class)
    fun register(query: SessionQuery.Register): Observable<User>

    @Handle(SessionQuery.SignIn::class)
    fun signIn(query: SessionQuery.SignIn): Observable<User>

    @Handle(SessionQuery.SignOut::class)
    fun signOut(query: SessionQuery.SignOut): Observable<User>

}