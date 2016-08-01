package com.rus.chat.ui.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rus.chat.App
import com.rus.chat.R
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.presenters.chat.ChatPresenter
import com.rus.chat.presenters.chat.ChatPresenterImpl
import com.rus.chat.presenters.chat.ChatPresenterImpl_Factory
import com.rus.chat.ui.adapter.ChatAdapter
import com.rus.chat.ui.adapter.ConversationsAdapter
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.showSnackBar
import com.rus.chat.utils.toast
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_conversations.*
import java.util.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatView, ChatAdapter.OnItemClickListener {

    @Inject
    lateinit var chatPresenter: ChatPresenterImpl
    lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        (application as App).chatComponent.inject(this)

        chatAdapter = ChatAdapter(this, mutableListOf<Message>())
        messages_recycler.adapter = chatAdapter

        val conversation = intent.extras.getSerializable(ConversationsActivity.EXTRA_CONVERSATION) as ConversationModel
        supportActionBar?.title = conversation.name

        messages_recycler.layoutManager = LinearLayoutManager(this)
        button_send.setOnClickListener { chatPresenter.sendMessage(edit_message.text.toString()) }

        chatPresenter.attachView(this)
        chatPresenter.initialize(conversation.id)

    }

    override fun setMessages(messages: List<Message>) {
    }

    override fun addMessage(message: Message) {
        chatAdapter.items.add(message)
        chatAdapter.notifyItemInserted(chatAdapter.itemCount)
    }

    override fun changeMessage(message: Message) {
        val position = chatAdapter.items.indexOfFirst { it.id.equals(message.id) }
        chatAdapter.items[position] = message
        chatAdapter.notifyItemChanged(position)
    }

    override fun removeMessage(message: Message) {
        val position = chatAdapter.items.indexOfFirst { it.id.equals(message) }
        chatAdapter.items.removeAt(position)
        chatAdapter.notifyItemRemoved(position)
    }

    override fun onItemClicked(item: Message) {
    }

    override fun onLongItemClicked(item: Message) {
    }

    override fun onError(throwable: Throwable) {
        showToast(throwable.toString())
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun showSnackbar(message: String) {
        messages_recycler.showSnackBar(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        chatPresenter.onDestroy()
    }
}
