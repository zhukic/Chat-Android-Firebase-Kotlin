package com.rus.chat.interactors.session

import com.rus.chat.entity.session.Query
import com.rus.chat.entity.session.SessionQuery
import com.rus.chat.interactors.UseCase
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 18.07.2016.
 */
@UseCase
class AddUserToDb : SessionUseCase() {

    override fun <User> execute(query: Query, subscriber: Subscriber<User>) {
        this.sessionRepository
                .query<User>(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }
}