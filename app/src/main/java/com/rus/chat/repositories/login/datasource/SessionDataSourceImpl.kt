package com.rus.chat.repositories.login.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.App
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.session.UserMapper
import com.rus.chat.net.FirebaseAPI
import com.rus.chat.utils.Logger
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

    override fun getCurrentUser(query: SessionQuery.GetCurrentUser): Observable<User> = Observable.just(firebaseAuth.currentUser)
            .map { firebaseUser -> UserMapper.transformFromFirebaseUser(firebaseUser) }

    override fun register(query: SessionQuery.Register): Observable<User> = Observable.create<FirebaseUser> { subscriber ->
        firebaseAuth.createUserWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task ->
                    subscriber.onNext(task.user)
                    subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }
            .flatMap { firebaseUser -> addUserNameToDb(firebaseUser.uid, query.name) }

    override fun signIn(query: SessionQuery.SignIn): Observable<User> = Observable.create<FirebaseUser> { subscriber ->
        firebaseAuth.signInWithEmailAndPassword(query.email, query.password)
                .addOnSuccessListener { task ->
                    subscriber.onNext(task.user)
                    subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }
            .map { firebaseUser -> UserMapper.transformFromFirebaseUser(firebaseUser) }

    override fun signOut(query: SessionQuery.SignOut): Observable<User> = Observable.create { subscriber ->
        firebaseAuth.signOut()
        subscriber.onCompleted()
    }

    private fun addUserNameToDb(uid: String, name: String): Observable<User> = Observable.create { subscriber ->
        val user = User(uid, name)
        FirebaseDatabase.getInstance().reference.child("users").child(uid).setValue(user)
                .addOnSuccessListener { subscriber.onNext(user)
                                        subscriber.onCompleted() }
                .addOnFailureListener { exception -> subscriber.onError(exception) }
    }

}