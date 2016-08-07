package com.rus.chat.di.chat

import com.rus.chat.ui.chat.ChatActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by RUS on 23.07.2016.
 */
@Subcomponent(modules = arrayOf(ChatModule::class))
@ChatScope
interface ChatComponent {

    fun inject(chatActivity: ChatActivity)

}