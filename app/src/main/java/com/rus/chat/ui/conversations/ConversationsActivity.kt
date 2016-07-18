package com.rus.chat.ui.conversations

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.rus.chat.R
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.presenters.conversations.ConversationsPresenter
import com.rus.chat.presenters.conversations.ConversationsPresenterImpl
import com.rus.chat.ui.adapter.ConversationsAdapter
import com.rus.chat.ui.login.LoginActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.showSnackBar
import kotlinx.android.synthetic.main.activity_conversations.*
import kotlinx.android.synthetic.main.content_conversations.*

class ConversationsActivity : AppCompatActivity(), ConversationsView, ConversationsAdapter.OnItemClickListener {

    lateinit var conversationsPresenter: ConversationsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)
        setSupportActionBar(toolbar)

        conversationsPresenter = ConversationsPresenterImpl(this)

        conversations_recyclerView.layoutManager = LinearLayoutManager(this)
        conversations_recyclerView.adapter = ConversationsAdapter(this, listOf(1,2,3,4,5))

        fab.setOnClickListener { conversationsPresenter.getConversations() }
    }

    override fun onStart() {
        super.onStart()
        //conversationsPresenter.getConversations()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun onItemClicked(position: Int) {
    }

    override fun onLongItemClicked(position: Int) {
    }

    override fun setConversations(conversations: List<Conversation>) {
        conversations_recyclerView.adapter = ConversationsAdapter(this, listOf())
    }

    override fun onError(throwable: Throwable?) {
        Logger.log(throwable?.message)
        conversations_recyclerView.showSnackBar(throwable?.message)
    }

    override fun showSnackbar(message: String?) {
        conversations_recyclerView.showSnackBar(message)
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
    }

}
