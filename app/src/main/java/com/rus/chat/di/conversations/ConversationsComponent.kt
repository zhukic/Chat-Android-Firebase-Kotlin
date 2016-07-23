package com.rus.chat.di.conversations

import com.rus.chat.di.session.SessionModule
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.interactors.session.SessionUseCase
import com.rus.chat.ui.conversations.ConversationsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Component(modules = arrayOf(ConversationsModule::class))
@Singleton
interface ConversationsComponent {

    fun inject(conversationsActivity: ConversationsActivity)

}