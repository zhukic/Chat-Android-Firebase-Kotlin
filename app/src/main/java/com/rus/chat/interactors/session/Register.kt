package com.rus.chat.interactors.session

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
class Register {

    fun execute(email: String, password: String, subscriber: Subscriber<Unit>) {
        SessionRepository()
                .query(SessionQuery.Register(email, password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}