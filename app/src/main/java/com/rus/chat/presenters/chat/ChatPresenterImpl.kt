package com.rus.chat.presenters.chat

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.ui.chat.ChatView
import com.rus.chat.utils.Logger
import rx.Subscriber
import javax.inject.Inject

/**
 * Created by RUS on 23.07.2016.
 */
class ChatPresenterImpl @Inject constructor(val chatUseCase: ChatUseCase) : ChatPresenter {

    var chatView: ChatView? = null

    override fun attachView(chatView: ChatView) {
        this.chatView = chatView
    }

    override fun initialize(conversationId: String) {
        chatUseCase.execute(ChatQuery.GetChatMessages(conversationId), MessageSubscriber())
    }

    override fun sendMessage(messageText: String) {
    }

    override fun onDestroy() {
        this.chatView = null
        this.chatUseCase.unsubscribe()
    }

    private inner class MessageSubscriber : Subscriber<Message>() {

        override fun onCompleted() {
        }

        override fun onError(throwable: Throwable) {
        }

        override fun onNext(message: Message?) {
            Logger.log(message.toString())
        }

    }
}