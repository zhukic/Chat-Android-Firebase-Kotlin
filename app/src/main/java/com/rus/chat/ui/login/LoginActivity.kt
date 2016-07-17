package com.rus.chat.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.R
import com.rus.chat.presenters.login.LoginPresenter
import com.rus.chat.presenters.login.LoginPresenterImpl
import com.rus.chat.ui.conversations.ConversationsActivity
import com.rus.chat.utils.Logger
import com.rus.chat.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenterImpl(this)

        signInButton.setOnClickListener { loginPresenter.signIn(email.text.toString(), password.text.toString()) }
        registerButton.setOnClickListener { loginPresenter.register(email.text.toString(), password.text.toString()) }

        if(intent.extras?.getBoolean(ConversationsActivity.EXTRA_SIGN_OUT) ?: false) loginPresenter.signOut()
        else loginPresenter.initialize()
    }

    override fun openConversationsActivity() {
        val intent = Intent(this, ConversationsActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginError(t: Throwable) = toast("${t.javaClass.name}: ${t.message}")

    override fun showToast(message: String) = toast(message)

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
        this.loginPresenter.onDestroy()
    }

}
