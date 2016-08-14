package com.rus.chat.di.app

import com.rus.chat.di.chat.ChatComponent
import com.rus.chat.di.chat.ChatModule
import com.rus.chat.di.conversations.ConversationsComponent
import com.rus.chat.di.conversations.ConversationsModule
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.di.session.SessionComponent
import com.rus.chat.di.session.SessionModule
import com.rus.chat.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 03.08.2016.
 */
@Component(modules = arrayOf(AppModule::class, FirebaseModule::class, NetModule::class))
@Singleton
interface AppComponent {
    fun addChatComponent(chatModule: ChatModule): ChatComponent
    fun addSessionComponent(sessionModule: SessionModule): SessionComponent
    fun addConversationsComponent(conversationModule: ConversationsModule): ConversationsComponent

    fun inject(mainActivity: MainActivity)
}