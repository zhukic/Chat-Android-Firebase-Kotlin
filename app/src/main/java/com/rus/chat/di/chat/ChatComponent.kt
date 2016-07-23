package com.rus.chat.di.chat

import com.rus.chat.ui.chat.ChatActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 23.07.2016.
 */
@Component(modules = arrayOf(ChatModule::class))
@Singleton
interface ChatComponent {

    fun inject(chatActivity: ChatActivity)

}