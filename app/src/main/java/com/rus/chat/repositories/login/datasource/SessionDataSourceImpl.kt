package com.rus.chat.repositories.login.datasource

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.utils.Logger
import rx.Observable
import rx.lang.kotlin.toSingletonObservable
import java.net.Authenticator
import javax.inject.Inject

/**
 * Created by RUS on 11.07.2016.
 */
class SessionDataSourceImpl : SessionDataSource {

    @Inject
    lateinit var context: Context

    init {
        App.appComponent.inject(this)
    }

    override fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<FirebaseUser> = Observable.just(FirebaseAuth.getInstance().currentUser)

    override fun register(query: SessionQuery.Register): Observable<FirebaseUser> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signIn(query: SessionQuery.SignIn): Observable<FirebaseUser> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task -> subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signOut(query: SessionQuery.SignOut): Observable<FirebaseUser> = Observable.create { subscriber ->
        FirebaseAuth.getInstance().signOut()
        subscriber.onCompleted()
    }

}