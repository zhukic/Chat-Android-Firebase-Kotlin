package com.rus.chat.presenters.chat

import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.ui.chat.ChatView
import javax.inject.Inject

/**
 * Created by RUS on 23.07.2016.
 */
class ChatPresenterImpl @Inject constructor(chatUseCase: ChatUseCase) : ChatPresenter {

    var chatView: ChatView? = null

    override fun attachView(chatView: ChatView) {
        this.chatView = chatView
    }

    override fun initialize() {
    }

    override fun sendMessage() {
    }

    override fun onDestroy() {
        this.chatView = null
    }
}