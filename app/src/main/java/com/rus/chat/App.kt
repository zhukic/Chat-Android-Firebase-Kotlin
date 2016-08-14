package com.rus.chat

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.di.app.AppComponent
import com.rus.chat.di.app.AppModule
import com.rus.chat.di.app.DaggerAppComponent
import com.rus.chat.di.chat.ChatComponent
import com.rus.chat.di.chat.ChatModule
import com.rus.chat.di.conversations.ConversationsComponent
import com.rus.chat.di.conversations.ConversationsModule
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.di.session.SessionComponent
import com.rus.chat.di.session.SessionModule
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.repositories.session.datasource.SessionDataSourceImpl
import com.rus.chat.utils.HandleUtils
import com.rus.chat.utils.Logger

/**
 * Created by RUS on 12.07.2016.
 */
class App : Application() {

    lateinit var appComponent: AppComponent
    private var sessionComponent: SessionComponent? = null
    private var conversationsComponent: ConversationsComponent? = null
    private var chatComponent: ChatComponent? = null

    override fun onCreate() {
        super.onCreate()

        createAppComponent()
    }

    fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun addSessionComponent(): SessionComponent {
        if(sessionComponent == null) {
            sessionComponent = appComponent.addSessionComponent(SessionModule())
        }
        return sessionComponent as SessionComponent
    }

    fun addConversationsComponent(): ConversationsComponent {
        if(conversationsComponent == null) {
            conversationsComponent = appComponent.addConversationsComponent(ConversationsModule())
        }
        return conversationsComponent as ConversationsComponent
    }

    fun addChatComponent(): ChatComponent {
        if(chatComponent == null) {
            chatComponent = appComponent.addChatComponent(ChatModule())
        }
        return chatComponent as ChatComponent
    }

    fun clearSessionComponent() {
        sessionComponent = null
    }

    fun clearConversationsComponent() {
        conversationsComponent = null
    }

    fun clearChatComponent() {
        chatComponent = null
    }

}