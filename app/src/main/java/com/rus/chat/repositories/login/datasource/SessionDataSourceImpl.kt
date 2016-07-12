package com.rus.chat.repositories.login.datasource

import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.utils.Logger
import rx.Observable

/**
 * Created by RUS on 11.07.2016.
 */
class SessionDataSourceImpl : SessionDataSource {

    override fun register(query: SessionQuery.Register): Observable<Unit> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signIn(query: SessionQuery.SignIn): Observable<Unit> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signOut(query: SessionQuery.SignOut): Observable<Unit> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signOut()
        subscriber.onCompleted()
    }

}