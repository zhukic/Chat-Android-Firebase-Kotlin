package com.rus.chat.repositories.conversations.datasource

import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.App
import com.rus.chat.entity.conversation.User
import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsDataSourceImpl : ConversationsDataSource {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        App.netComponent.inject(this)
    }

    override fun getConversations(): Observable<List<User>> = retrofit.create(FirebaseService::class.java).getConversations()

}