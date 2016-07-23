package com.rus.chat.presenters.conversations

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.conversation.ConversationsQuery
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.ui.conversations.ConversationsView
import com.rus.chat.utils.Logger
import rx.Subscriber
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
        useCase.execute(ConversationsQuery.Initialize(), ResponseSubscriber())
    }

    override fun onCreateConversationButtonClicked() {
        conversationsView?.showCreateConversationFragment()
    }

    override fun createConversation(conversationName: String) {
        conversationsView?.showProgress()
        useCase.execute(ConversationsQuery.CreateConversation(Conversation(name = conversationName)), CreateConversationSubscriber())
    }

    override fun onDestroy() {
        this.conversationsView = null
        this.useCase.unsubscribe()
    }

    private inner class CreateConversationSubscriber : Subscriber<Conversation>() {

        override fun onError(throwable: Throwable?) {
            if(throwable != null) conversationsView?.onError(throwable)
        }

        override fun onNext(conversation: Conversation?) {
            //if(conversation != null) conversationsView?.addConversation(conversation)
        }

        override fun onCompleted() {
            conversationsView?.hideProgress()
        }

    }

    private inner class ResponseSubscriber : Subscriber<ConversationResponse.Response>() {

        override fun onError(throwable: Throwable?) {
            Logger.log("response: Error")
            if(throwable != null)conversationsView?.onError(throwable)
        }

        override fun onCompleted() {}

        override fun onNext(response: ConversationResponse.Response?) {
            Logger.log("response: next")
            when(response) {
                is ConversationResponse.ConversationAdded -> conversationsView?.addConversation(response.body)
                is ConversationResponse.ConversationChanged -> conversationsView?.changeConversation(response.body)
                is ConversationResponse.ConversationRemoved -> conversationsView?.removeConversation(response.body)
            }
        }

    }
}