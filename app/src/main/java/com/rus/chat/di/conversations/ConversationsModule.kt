package com.rus.chat.di.conversations

import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.interactors.conversations.ConversationsUseCase
import com.rus.chat.interactors.conversations.CreateConversation
import com.rus.chat.interactors.conversations.GetConversations
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
@Module
class ConversationsModule() {

    @Provides
    @ConversationsScope
    fun getGetConversations(conversationsRepository: ConversationsRepository): GetConversations = GetConversations(conversationsRepository)

    @Provides
    @ConversationsScope
    fun getCreateConversation(conversationsRepository: ConversationsRepository): CreateConversation = CreateConversation(conversationsRepository)

    @Provides
    @ConversationsScope
    fun getConversationRepository(conversationsDataSourceImpl: ConversationsDataSourceImpl): ConversationsRepository = ConversationsRepository(conversationsDataSourceImpl)

    @Provides
    @ConversationsScope
    fun getConversationDataSourceImplementation(retrofit: Retrofit, firebaseDatabase: FirebaseDatabase): ConversationsDataSourceImpl = ConversationsDataSourceImpl(retrofit, firebaseDatabase)

}