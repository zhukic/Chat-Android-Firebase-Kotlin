package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 10.07.2016.
 */
@UseCase
class SignIn : SessionUseCase() {

    override fun execute(email: String, password: String, subscriber: Subscriber<FirebaseUser>) {
        this.sessionRepository
                .query(SessionQuery.SignIn(email, password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}