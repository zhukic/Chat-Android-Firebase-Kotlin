package com.rus.chat.di.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.interactors.chat.ChatUseCase
import com.rus.chat.interactors.chat.GetConversationMessages
import com.rus.chat.interactors.chat.SendMessage
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
@Module
class ChatModule {

    @Provides
    @ChatScope
    fun getGetConversationMessages(chatRepository: ChatRepository): GetConversationMessages = GetConversationMessages(chatRepository)

    @Provides
    @ChatScope
    fun getSendMessage(chatRepository: ChatRepository): SendMessage = SendMessage(chatRepository)

    @Provides
    @ChatScope
    fun getChatRepository(chatDataSourceImpl: ChatDataSourceImpl): ChatRepository = ChatRepository(chatDataSourceImpl)

    @Provides
    @ChatScope
    fun getChatDataSourceImpl(retrofit: Retrofit, firebaseDatabase: FirebaseDatabase, firebaseAuth: FirebaseAuth): ChatDataSourceImpl
            = ChatDataSourceImpl(retrofit, firebaseDatabase, firebaseAuth)

}