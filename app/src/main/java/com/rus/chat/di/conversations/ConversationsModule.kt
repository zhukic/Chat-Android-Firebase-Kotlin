package com.rus.chat.di.conversations

import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.conversations.ConversationsRepository
import com.rus.chat.repositories.conversations.datasource.ConversationsDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Module(includes = arrayOf(NetModule::class, FirebaseModule::class))
class ConversationsModule() {

    @Provides
    @Singleton
    fun getConversationUseCase(conversationsRepository: ConversationsRepository): ConversationsUseCase = ConversationsUseCase(conversationsRepository)

    @Provides
    @Singleton
    fun getConversationRepository(conversationsDataSourceImpl: ConversationsDataSourceImpl): BaseRepository = ConversationsRepository(conversationsDataSourceImpl)

    @Provides
    @Singleton
    fun getConversationDataSourceImplementation(retrofit: Retrofit, firebaseDatabase: FirebaseDatabase): ConversationsDataSourceImpl = ConversationsDataSourceImpl(retrofit, firebaseDatabase)

}