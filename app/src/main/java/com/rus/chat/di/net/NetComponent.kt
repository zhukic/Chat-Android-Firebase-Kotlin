package com.rus.chat.di.net

import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 17.07.2016.
 */
@Component(modules = arrayOf(NetModule::class))
@Singleton
interface NetComponent {
    fun inject(conversationsDataSourceImpl: ConversationsDataSourceImpl)
}