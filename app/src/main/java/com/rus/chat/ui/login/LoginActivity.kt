package com.rus.chat.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.App
import com.rus.chat.R
import com.rus.chat.entity.session.User
import com.rus.chat.presenters.login.LoginPresenter
import com.rus.chat.presenters.login.LoginPresenterImpl
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).addSessionComponent().inject(this)

        loginPresenter.attachView(this)

        signInButton.setOnClickListener { loginPresenter.signIn(name.text.toString(), password.text.toString()) }
        registerButton.setOnClickListener { loginPresenter.register(name.text.toString(), password.text.toString()) }

        if(intent.extras?.getBoolean(ConversationsActivity.EXTRA_SIGN_OUT) ?: false) loginPresenter.signOut()
    }

    override fun openConversationsActivity(uid: String) {
        val intent = Intent(this, ConversationsActivity::class.java)
        intent.putExtra(KEY_UID, uid)
        startActivity(intent)
        finish()
    }

    override fun onError(throwable: Throwable) = toast("${throwable.javaClass.name}: ${throwable.message}")

    override fun showToast(message: String) = toast(message)

    override fun showSnackbar(message: String) {}

    override fun showSignInProgress() {
        signInButton.visibility = View.GONE
        signInProgress.visibility = View.VISIBLE
    }

    override fun hideSignInProgress() {
        signInButton.visibility = View.VISIBLE
        signInProgress.visibility = View.GONE
    }

    override fun showRegisterProgress() {
        registerButton.visibility = View.GONE
        registerProgress.visibility = View.VISIBLE
    }

    override fun hideRegisterProgress() {
        registerButton.visibility = View.VISIBLE
        registerProgress.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).clearSessionComponent()
        this.loginPresenter.onDestroy()
    }

    companion object {
        val KEY_UID: String = "UID"
    }
}
