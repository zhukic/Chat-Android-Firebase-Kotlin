package com.rus.chat.ui.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rus.chat.App
import com.rus.chat.R
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.presenters.chat.ChatPresenter
import com.rus.chat.presenters.chat.ChatPresenterImpl
import com.rus.chat.presenters.chat.ChatPresenterImpl_Factory
import com.rus.chat.ui.adapter.ChatAdapter
import com.rus.chat.ui.adapter.ConversationsAdapter
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.toast
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_conversations.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatView, ChatAdapter.OnItemClickListener {

    @Inject
    lateinit var chatPresenter: ChatPresenterImpl
    lateinit var chatAdapter: ChatAdapter
    lateinit var conversationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        conversationId = intent.extras.getString(ConversationsActivity.EXTRA_CHAT_ID)

        (application as App).chatComponent.inject(this)

        chatAdapter = ChatAdapter(this, mutableListOf<Message>())

        messages_recycler.layoutManager = LinearLayoutManager(this)
        button_send.setOnClickListener { chatPresenter.sendMessage(edit_message.text.toString()) }

        chatPresenter.attachView(this)
        chatPresenter.initialize(conversationId)

    }

    override fun setMessages(messages: List<Message>) {
        messages_recycler.adapter = ChatAdapter(this, messages.toMutableList())
    }

    override fun onItemClicked(item: Message) {
    }

    override fun onLongItemClicked(item: Message) {
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
