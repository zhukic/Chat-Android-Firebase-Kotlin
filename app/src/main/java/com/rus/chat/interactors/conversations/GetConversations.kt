package com.rus.chat.interactors.conversations

import com.rus.chat.entity.conversation.User
import com.rus.chat.interactors.UseCase
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by RUS on 17.07.2016.
 */
@UseCase
class GetConversations : ConversationUseCase() {

    override fun execute(subscriber: Subscriber<List<User>>) {
        conversationRepository
                .query()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}