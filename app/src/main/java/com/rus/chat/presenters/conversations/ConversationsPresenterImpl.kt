package com.rus.chat.presenters.conversations

import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.interactors.conversations.GetConversations
import com.rus.chat.ui.conversations.ConversationsView
import com.rus.chat.utils.Logger
import rx.Subscriber

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsPresenterImpl(var conversationsView: ConversationsView?) : ConversationsPresenter {

    override fun getConversations() {
        this.conversationsView?.showProgress()
        GetConversations().execute(ConversationsSubscriber())
    }

    override fun onDestroy() {
        this.conversationsView = null
    }

    private inner class ConversationsSubscriber : Subscriber<List<User>>() {

        override fun onError(e: Throwable?) {
            Logger.log("error")
            conversationsView?.onError(e)
        }

        override fun onNext(user: List<User>?) {
            user?.forEach { Logger.log(it.toString()) }
            Logger.log("next")
            //conversationsView?.setConversations(conversations!!)
        }

        override fun onCompleted() {
            Logger.log("completed")
            conversationsView?.hideProgress()
        }

    }
}