package com.rus.chat.repositories.login.datasource

import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.utils.Logger
import rx.Observable

/**
 * Created by RUS on 11.07.2016.
 */
class SessionDataSource : ISessionDataSource {

    override fun register(email: String, password: String): Observable<Any> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signIn(email: String, password: String): Observable<Any> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signOut(): Observable<Any> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signOut()
        subscriber.onCompleted()
    }

}