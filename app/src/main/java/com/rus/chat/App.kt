package com.rus.chat

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.di.chat.ChatComponent
import com.rus.chat.di.chat.ChatModule
import com.rus.chat.di.chat.DaggerChatComponent
import com.rus.chat.di.conversations.ConversationsComponent
import com.rus.chat.di.conversations.ConversationsModule
import com.rus.chat.di.conversations.DaggerConversationsComponent
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.DaggerNetComponent
import com.rus.chat.di.net.NetComponent
import com.rus.chat.di.net.NetModule
import com.rus.chat.di.session.DaggerSessionComponent
import com.rus.chat.di.session.SessionComponent
import com.rus.chat.di.session.SessionModule
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.utils.HandleUtils
import com.rus.chat.utils.Logger

/**
 * Created by RUS on 12.07.2016.
 */
class App : Application() {

    lateinit var netComponent: NetComponent
    lateinit var sessionComponent: SessionComponent
    lateinit var conversationsComponent: ConversationsComponent
    lateinit var chatComponent: ChatComponent

    override fun onCreate() {
        super.onCreate()

        createAppComponent()
        createNetComponent()
        createSessionComponent()
        createConversationsComponent()
        createChatComponent()
    }

    private fun createAppComponent() {
        /*appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()*/
    }

    private fun createNetComponent() {
        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule())
                .build()
    }

    private fun createSessionComponent() {
        sessionComponent = DaggerSessionComponent.builder()
                .sessionModule(SessionModule())
                .build()
    }

    private fun createConversationsComponent() {
        conversationsComponent = DaggerConversationsComponent.builder()
                .conversationsModule(ConversationsModule())
                .build()
    }

    private fun createChatComponent() {
        chatComponent = DaggerChatComponent.builder()
                .chatModule(ChatModule())
                .build()
    }

}