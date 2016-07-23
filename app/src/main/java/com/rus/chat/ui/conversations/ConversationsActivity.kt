package com.rus.chat.ui.conversations

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.rus.chat.App

import com.rus.chat.R
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.presenters.conversations.ConversationsPresenter
import com.rus.chat.presenters.conversations.ConversationsPresenterImpl
import com.rus.chat.ui.adapter.ConversationsAdapter
import com.rus.chat.ui.chat.ChatActivity
import com.rus.chat.ui.login.LoginActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.showSnackBar
import com.rus.chat.utils.toast
import kotlinx.android.synthetic.main.activity_conversations.*
import kotlinx.android.synthetic.main.content_conversations.*
import java.util.*
import javax.inject.Inject

class ConversationsActivity : AppCompatActivity(), ConversationsView, ConversationsAdapter.OnItemClickListener {

    @Inject
    lateinit var conversationsPresenter: ConversationsPresenterImpl
    lateinit var conversationsAdapter: ConversationsAdapter
    private val progressDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.conversations_loading)
                .content(R.string.wait)
                .progress(true, 0)
                .build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)
        setSupportActionBar(toolbar)

        (application as App).conversationsComponent.inject(this)
        conversationsAdapter = ConversationsAdapter(this, mutableListOf<Conversation>())
        conversations_recyclerView.adapter = conversationsAdapter

        conversations_recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { conversationsPresenter.onCreateConversationButtonClicked() }

        conversationsPresenter.attachView(this)

    }

    override fun onStart() {
        super.onStart()
        setProgressBarIndeterminateVisibility(true)
        conversationsPresenter.initialize()
    }

    override fun showCreateConversationFragment() {
        MaterialDialog.Builder(this)
                .title(R.string.create_conversation)
                .content(R.string.input_conversation_name)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText(R.string.create_conversation)
                .input("", "", false) { dialog, input -> conversationsPresenter.createConversation(input.toString()) }.show()
    }

    override fun addConversation(conversation: Conversation?) {
        if(conversation != null) {
            conversationsAdapter.items.add(conversation)
            conversationsAdapter.notifyItemInserted(conversationsAdapter.itemCount - 1)
        }
    }

    override fun changeConversation(conversation: Conversation?) {
        if(conversation != null) {
            val index = (conversations_recyclerView.adapter as ConversationsAdapter).items.indexOfFirst { it.id.equals(conversation.id) }
            (conversations_recyclerView.adapter as ConversationsAdapter).items[index] = conversation
            conversations_recyclerView.adapter.notifyDataSetChanged()
        }
    }

    override fun removeConversation(conversation: Conversation?) {
        Logger.log("removed")
        if(conversation != null) {
            (conversations_recyclerView.adapter as ConversationsAdapter).items.removeAll { it.id.equals(conversation.id) }
            conversations_recyclerView.adapter.notifyDataSetChanged()
        }
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.hide()
    }

    override fun onItemClicked(item: Conversation) {
        openChatActivity(item.id)
    }

    override fun onLongItemClicked(item: Conversation) {
    }

    override fun setConversations(conversations: List<Conversation>?) {
        conversations_recyclerView.adapter = ConversationsAdapter(this, conversations!!.toMutableList())
    }

    override fun openChatActivity(chatId: String) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(EXTRA_CHAT_ID, chatId)
        startActivity(intent)
    }

    override fun onError(throwable: Throwable) {
        Logger.log(throwable?.message)
        conversations_recyclerView.showSnackBar(throwable?.message)
    }

    override fun showSnackbar(message: String) {
        conversations_recyclerView.showSnackBar(message)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(EXTRA_SIGN_OUT, true)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_conversations, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_sign_out -> openLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        val EXTRA_SIGN_OUT: String = "SIGN_OUT"
        val EXTRA_CHAT_ID: String = "CHAT_ID"
    }

}
