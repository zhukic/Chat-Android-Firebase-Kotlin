package com.rus.chat.interactors.session

import com.rus.chat.entity.query.session.SessionQuery
import com.rus.chat.entity.session.User
import com.rus.chat.interactors.UseCase
import com.rus.chat.repositories.session.SessionRepository
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by RUS on 01.08.2016.
 */
@UseCase
class GetCurrentUser @Inject constructor(sessionRepository: SessionRepository) : SessionUseCase(sessionRepository) {

    fun execute(subscriber: Subscriber<User>) = super.execute(SessionQuery.GetCurrentUser(), subscriber)

}