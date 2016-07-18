package com.rus.chat.di

import com.rus.chat.di.net.NetModule
import com.rus.chat.repositories.BaseDataSource
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 14.07.2016.
 */
@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {

    fun inject(baseDataSource: BaseDataSource)

}