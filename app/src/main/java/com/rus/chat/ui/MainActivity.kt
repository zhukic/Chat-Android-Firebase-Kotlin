package com.rus.chat.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.App
import com.rus.chat.R
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.ui.login.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

        initialize()
    }

    private fun initialize() {
        if(firebaseAuth.currentUser != null)
            openConversationsActivity(firebaseAuth.currentUser!!.uid)
        else openLoginActivity()
        finish()
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun openConversationsActivity(uid: String) {
        val intent = Intent(this, ConversationsActivity::class.java)
        intent.putExtra(KEY_UID, uid)
        startActivity(intent)
    }

    companion object {
        val KEY_UID: String = "UID"
    }
}
