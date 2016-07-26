package com.rus.chat.di.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.chat.ChatRepository
import com.rus.chat.repositories.chat.datasource.ChatDataSourceImpl
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by RUS on 23.07.2016.
 */
@Module(includes = arrayOf(NetModule::class, FirebaseModule::class))
class ChatModule {

    @Provides
    @Singleton
    fun getChatUseCase(chatRepository: ChatRepository): ChatUseCase = ChatUseCase(chatRepository)

    @Provides
    @Singleton
    fun getChatRepository(chatDataSourceImpl: ChatDataSourceImpl): BaseRepository = ChatRepository(chatDataSourceImpl)

    @Provides
    @Singleton
    fun getChatDataSourceImpl(retrofit: Retrofit, firebaseDatabase: FirebaseDatabase, firebaseAuth: FirebaseAuth): ChatDataSourceImpl
            = ChatDataSourceImpl(retrofit, firebaseDatabase, firebaseAuth)

}