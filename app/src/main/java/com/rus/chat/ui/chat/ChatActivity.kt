package com.rus.chat.ui.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rus.chat.App
import com.rus.chat.R
import com.rus.chat.entity.chat.Message
import com.rus.chat.presenters.chat.ChatPresenter
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.toast

class ChatActivity : AppCompatActivity(), ChatView {

    lateinit var chatPresenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        (application as App).chatComponent.inject(this)

    }

    override fun setMessages(messages: List<Message>) {
    }

    override fun onError(throwable: Throwable) {
        showSnackbar(throwable.message.toString())
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun showSnackbar(message: String) {
    }
}
