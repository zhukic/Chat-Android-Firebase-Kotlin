package com.rus.chat.presenters.chat

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.MessageResponse
import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.ui.chat.ChatView
import com.rus.chat.utils.Logger
import rx.Subscriber
import rx.observers.Subscribers
import javax.inject.Inject

/**
 * Created by RUS on 23.07.2016.
 */
class ChatPresenterImpl @Inject constructor(val chatUseCase: ChatUseCase) : ChatPresenter {

    var chatView: ChatView? = null
    lateinit var conversationId: String

    override fun attachView(chatView: ChatView) {
        this.chatView = chatView
    }

    override fun initialize(conversationId: String) {
        this.conversationId = conversationId
        Logger.log(conversationId)
        chatUseCase.execute(ChatQuery.GetChatMessages(conversationId), MessageSubscriber())
    }

    override fun sendMessage(messageText: String) {
        chatUseCase.execute<Any>(ChatQuery.SendMessage(Message(conversationId = conversationId, text = messageText)))
    }

    override fun onDestroy() {
        this.chatView = null
        this.chatUseCase.unsubscribe()
    }

    private inner class MessageSubscriber : Subscriber<MessageResponse.Response>() {

        override fun onCompleted() {
        }

        override fun onError(throwable: Throwable) {
            chatView?.onError(throwable)
        }

        override fun onNext(messageResponse: MessageResponse.Response?) {
            Logger.log("response")
            when(messageResponse) {
                is MessageResponse.MessageAdded -> chatView?.addMessage(messageResponse.body)
                is MessageResponse.MessageChanged -> chatView?.changeMessage(messageResponse.body)
                is MessageResponse.MessageRemoved -> chatView?.removeMessage(messageResponse.body)
            }
        }

    }
}