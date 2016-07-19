package com.rus.chat.repositories.conversations.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.App
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.net.FirebaseAPI
import com.rus.chat.utils.Logger
import com.rus.chat.utils.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import rx.Observable
import java.util.*
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

    override fun getConversations(query: BaseQuery): Observable<List<Conversation>> = retrofit.create(FirebaseAPI::class.java).getConversations()

}