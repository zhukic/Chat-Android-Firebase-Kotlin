package com.rus.chat.presenters.conversations

import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.conversation.ConversationsQuery
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.ui.conversations.ConversationsView
import com.rus.chat.utils.Logger
import rx.Subscriber

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsPresenterImpl(var conversationsView: ConversationsView?) : ConversationsPresenter {

    val useCase: ConversationsUseCase = ConversationsUseCase()

    override fun getConversations() {
        this.conversationsView?.showProgress()
        useCase.execute(ConversationsQuery.GetConversations(), ConversationsSubscriber())
    }

    override fun onDestroy() {
        this.conversationsView = null
        this.useCase.unsubscribe()
    }

    private inner class ConversationsSubscriber : Subscriber<List<Conversation>>() {

        override fun onError(e: Throwable?) {
            Logger.log("error")
            conversationsView?.onError(e)
        }

        override fun onNext(conversations: List<Conversation>?) {
            conversationsView?.setConversations(conversations)
        }

        override fun onCompleted() {
            conversationsView?.hideProgress()
        }

    }
}