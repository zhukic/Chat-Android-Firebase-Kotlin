package com.rus.chat.interactors.session

import com.google.firebase.auth.FirebaseUser
import com.rus.chat.App
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.login.SessionRepository
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 12.07.2016.
 */
@UseCase
class SignOut : SessionUseCase() {

    override fun execute(email: String, password: String, subscriber: Subscriber<FirebaseUser>) {
        this.sessionRepository
                .query(SessionQuery.SignOut())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}