package com.rus.chat.presenters.chat

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.response.MessageResponse
import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.interactors.chat.GetConversationMessages
import com.rus.chat.interactors.chat.SendMessage
import com.rus.chat.ui.chat.ChatView
import com.rus.chat.utils.Logger
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import rx.Subscriber
import rx.observers.Subscribers
import javax.inject.Inject

/**
 * Created by RUS on 23.07.2016.
 */
class ChatPresenterImpl @Inject constructor(val getConversationMessages: GetConversationMessages,
                                            val sendMessage: SendMessage) : ChatPresenter {

    var chatView: ChatView? = null
    lateinit var conversationId: String

    override fun attachView(chatView: ChatView) {
        this.chatView = chatView
    }

    override fun initialize(conversationId: String) {
        this.conversationId = conversationId
        Logger.log(conversationId)
        getConversationMessages.execute(conversationId, MessageSubscriber())
    }

    override fun sendMessage(messageText: String) {
        val time = DateTime.now(DateTimeZone.getDefault()).toString()
        sendMessage.execute(conversationId, messageText, time, SendMessageSubscriber() )

    }

    override fun onDestroy() {
        this.chatView = null
        //this.chatUseCase.unsubscribe()
    }

    private inner class MessageSubscriber : Subscriber<MessageResponse.Response>() {

        override fun onCompleted() {
        }

        override fun onError(throwable: Throwable) {
            chatView?.onError(throwable)
        }

        override fun onNext(messageResponse: MessageResponse.Response?) {
            when(messageResponse) {
                is MessageResponse.MessageAdded -> chatView?.addMessage(messageResponse.body)
                is MessageResponse.MessageChanged -> chatView?.changeMessage(messageResponse.body)
                is MessageResponse.MessageRemoved -> chatView?.removeMessage(messageResponse.body)
            }
        }

    }

    private inner class SendMessageSubscriber : Subscriber<FirebaseResponse>() {
        override fun onNext(firebaseResponse: FirebaseResponse?) {
        }

        override fun onCompleted() {
        }

        override fun onError(throwable: Throwable?) {
            if(throwable != null) chatView?.onError(throwable)
        }

    }
}