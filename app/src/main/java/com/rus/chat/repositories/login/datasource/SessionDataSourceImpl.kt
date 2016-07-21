package com.rus.chat.repositories.login.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.net.FirebaseAPI
import retrofit2.Retrofit
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by RUS on 11.07.2016.
 */
class SessionDataSourceImpl(val retrofit: Retrofit, val firebaseAuth: FirebaseAuth) : SessionDataSource {

    override fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<FirebaseUser> = Observable.just(firebaseAuth.currentUser)

    override fun register(query: SessionQuery.Register): Observable<FirebaseUser> = Observable.create<FirebaseUser> { subscriber ->
        firebaseAuth.createUserWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task ->
                    subscriber.onNext(task.user)
                    subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }
            .doOnNext { firebaseUser -> addUserNameToDb(firebaseUser.uid, query.name)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(AddUserToDbSubscriber()) }

    override fun signIn(query: SessionQuery.SignIn): Observable<FirebaseUser> = Observable.create { subscriber ->
        firebaseAuth.signInWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task ->
                    subscriber.onNext(task.user)
                    subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

    override fun signOut(query: SessionQuery.SignOut): Observable<FirebaseUser> = Observable.create { subscriber ->
        firebaseAuth.signOut()
        subscriber.onCompleted()
    }

    private fun addUserNameToDb(uid: String, name: String): Observable<User> = retrofit.create(FirebaseAPI::class.java).addUser(uid, User(uid, name))

    private inner class AddUserToDbSubscriber : Subscriber<User>() {

        override fun onNext(t: User?) {}

        override fun onError(e: Throwable?) = throw RuntimeException(e)

        override fun onCompleted() {}

    }

}