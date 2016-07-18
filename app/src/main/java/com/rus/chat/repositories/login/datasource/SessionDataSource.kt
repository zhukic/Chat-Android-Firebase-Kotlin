package com.rus.chat.repositories.login.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.entity.session.Handle
import rx.Observable

/**
 * Created by RUS on 11.07.2016.
 */
interface SessionDataSource {

    @Handle(SessionQuery.GetCurrentUser::class)
    fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<FirebaseUser>

    @Handle(SessionQuery.Register::class)
    fun register(query: SessionQuery.Register): Observable<FirebaseUser>

    @Handle(SessionQuery.SignIn::class)
    fun signIn(query: SessionQuery.SignIn): Observable<FirebaseUser>

    @Handle(SessionQuery.SignOut::class)
    fun signOut(query: SessionQuery.SignOut): Observable<FirebaseUser>

}