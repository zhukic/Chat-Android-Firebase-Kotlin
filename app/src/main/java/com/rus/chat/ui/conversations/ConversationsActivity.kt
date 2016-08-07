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
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
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

        (application as App).addConversationsComponent().inject(this)
        conversationsAdapter = ConversationsAdapter(this, mutableListOf<ConversationModel>())
        conversations_recyclerView.adapter = conversationsAdapter

        conversations_recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { conversationsPresenter.onCreateConversationButtonClicked() }

        conversationsPresenter.attachView(this)
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

    override fun setConversations(conversationEntities: List<ConversationModel>?) {
        conversations_recyclerView.adapter = ConversationsAdapter(this, conversationEntities!!.toMutableList())
    }

    override fun addConversation(conversationEntity: ConversationModel) {
        conversationsAdapter.items.add(conversationEntity)
        conversationsAdapter.notifyItemInserted(conversationsAdapter.itemCount - 1)
    }

    override fun changeConversation(conversationEntity: ConversationModel) {
        val index = (conversations_recyclerView.adapter as ConversationsAdapter).items.indexOfFirst { it.id.equals(conversationEntity.id) }
        (conversations_recyclerView.adapter as ConversationsAdapter).items[index] = conversationEntity
        conversations_recyclerView.adapter.notifyDataSetChanged()
    }

    override fun removeConversation(conversationEntity: ConversationModel) {
        Logger.log("removed")
        (conversations_recyclerView.adapter as ConversationsAdapter).items.removeAll { it.id.equals(conversationEntity.id) }
        conversations_recyclerView.adapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.hide()
    }

    override fun onItemClicked(item: ConversationModel) {
        openChatActivity(item)
    }

    override fun onLongItemClicked(item: ConversationModel) {
    }

    override fun openChatActivity(conversationModel: ConversationModel) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(EXTRA_CONVERSATION, conversationModel)
        startActivity(intent)
    }

    override fun onError(throwable: Throwable) {
        Logger.log("${throwable.javaClass.name} ${throwable.message}")
        conversations_recyclerView.showSnackBar(throwable.message)
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

    override fun onDestroy() {
        super.onDestroy()
        (application as App).clearConversationsComponent()
        conversationsPresenter.onDestroy()
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
        val EXTRA_CONVERSATION: String = "CONVERSATION"
    }

}
