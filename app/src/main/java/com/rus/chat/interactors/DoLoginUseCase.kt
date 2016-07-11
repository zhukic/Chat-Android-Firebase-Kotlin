package com.rus.chat.interactors

import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSource
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 10.07.2016.
 */
class DoLoginUseCase {

    fun doLogin(email: String, password: String, subscriber: Subscriber<Any>) {
        SessionRepository()
                .signInQuery(email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}