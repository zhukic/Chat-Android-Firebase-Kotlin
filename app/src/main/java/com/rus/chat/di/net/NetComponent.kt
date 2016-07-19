package com.rus.chat.di.net

import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Component(modules = arrayOf(NetModule::class))
@Singleton
interface NetComponent {
    fun inject(sessionDataSourceImpl: SessionDataSourceImpl)
    fun inject(conversationsDataSourceImpl: ConversationsDataSourceImpl)
}