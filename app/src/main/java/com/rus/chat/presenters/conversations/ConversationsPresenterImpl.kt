package com.rus.chat.presenters.conversations

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.interactors.conversations.CreateConversation
import com.rus.chat.interactors.conversations.GetConversations
import com.rus.chat.ui.conversations.ConversationsView
import com.rus.chat.utils.Logger
import rx.Subscriber
import rx.observers.Subscribers
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsPresenterImpl @Inject constructor(val getConversations: GetConversations,
                                                     val createConversation: CreateConversation) : ConversationsPresenter {

    var conversationsView: ConversationsView? = null

    override fun attachView(conversationsView: ConversationsView) {
        this.conversationsView = conversationsView
    }

    override fun initialize() {
        getConversations.execute(ConversationsSubscriber())
    }

    override fun onCreateConversationButtonClicked() {
        conversationsView?.showCreateConversationFragment()
    }

    override fun createConversation(conversationName: String) {
        createConversation.execute(conversationName, CreateConversationSubscriber())
    }

    override fun onDestroy() {
        this.conversationsView = null
        //this.useCase.unsubscribe()
    }

    private inner class CreateConversationSubscriber : Subscriber<FirebaseResponse>() {

        override fun onNext(firebaseResponse: FirebaseResponse?) {
        }

        override fun onCompleted() {
        }

        override fun onError(throwable: Throwable?) {
            if(throwable != null) conversationsView?.onError(throwable)
        }

    }

    private inner class ConversationsSubscriber : Subscriber<ConversationResponse>() {

        override fun onError(throwable: Throwable?) {
            Logger.log("response: Error")
            if(throwable != null) conversationsView?.onError(throwable)
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