package com.rus.chat.repositories.login.datasource

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.repositories.BaseDataSource
import com.rus.chat.repositories.FirebaseAPI
import com.rus.chat.utils.Logger
import retrofit2.Retrofit
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.toSingletonObservable
import rx.schedulers.Schedulers
import java.net.Authenticator
import javax.inject.Inject

/**
 * Created by RUS on 11.07.2016.
 */
class SessionDataSourceImpl : BaseDataSource(), SessionDataSource {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        App.netComponent.inject(this)
    }

    override fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<FirebaseUser> = Observable.just(FirebaseAuth.getInstance().currentUser)

    override fun register(query: SessionQuery.Register): Observable<FirebaseUser> = Observable.create<FirebaseUser> { subscriber ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task ->
                    subscriber.onNext(task.user)
                    subscriber.onCompleted() }
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

    override fun addToDb(query: SessionQuery.AddToDb): Observable<User> = retrofit.create(FirebaseAPI::class.java).addUser(query.uid, User(query.name))

}