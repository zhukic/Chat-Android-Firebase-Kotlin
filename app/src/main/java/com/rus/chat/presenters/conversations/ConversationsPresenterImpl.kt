package com.rus.chat.presenters.conversations

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.ui.conversations.ConversationsView
import com.rus.chat.utils.Logger
import rx.Subscriber
import rx.observers.Subscribers
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsPresenterImpl @Inject constructor(val useCase: ConversationsUseCase) : ConversationsPresenter {

    var conversationsView: ConversationsView? = null

    override fun attachView(conversationsView: ConversationsView) {
        this.conversationsView = conversationsView
    }

    override fun initialize() {
        useCase.execute(ConversationsQuery.Initialize(), ConversationsSubscriber())
    }

    override fun onCreateConversationButtonClicked() {
        conversationsView?.showCreateConversationFragment()
    }

    override fun createConversation(conversationName: String) {
        useCase.execute(ConversationsQuery.CreateConversation(ConversationEntity(name = conversationName)), Subscribers.empty<Any>())
    }

    override fun onDestroy() {
        this.conversationsView = null
        this.useCase.unsubscribe()
    }

    private inner class ConversationsSubscriber : Subscriber<ConversationResponse>() {

        override fun onError(throwable: Throwable?) {
            Logger.log("response: Error")
            if(throwable != null)conversationsView?.onError(throwable)
        }

        override fun onCompleted() {}

        override fun onNext(response: ConversationResponse?) {
            when(response?.type) {
                ConversationResponse.Type.ADDED -> conversationsView?.addConversation(response?.conversation as ConversationModel)
                ConversationResponse.Type.CHANGED -> conversationsView?.changeConversation(response?.conversation as ConversationModel)
                ConversationResponse.Type.REMOVED -> conversationsView?.removeConversation(response?.conversation as ConversationModel)
            }
        }

    }
}