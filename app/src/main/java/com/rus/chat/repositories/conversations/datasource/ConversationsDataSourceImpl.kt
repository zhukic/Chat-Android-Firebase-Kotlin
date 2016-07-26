package com.rus.chat.repositories.conversations.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.rus.chat.App
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.net.FirebaseAPI
import com.rus.chat.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import rx.Observable
import rx.Subscriber
import rx.lang.kotlin.subscriber
import java.util.*
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsDataSourceImpl(val retrofit: Retrofit, val firebaseDatabase: FirebaseDatabase) : ConversationsDataSource {

    override fun initializeEventListener(query: ConversationsQuery.Initialize): Observable<ConversationResponse.Response> = Observable.create { subscriber ->
        firebaseDatabase.reference
                .child("conversations")
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(Conversation::class.java)
                            conversation.id = dataSnapshot.key
                            subscriber.onNext(ConversationResponse.ConversationChanged(conversation))
                        }
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(Conversation::class.java)
                            conversation.id = dataSnapshot.key
                            subscriber.onNext(ConversationResponse.ConversationAdded(conversation))
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(Conversation::class.java)
                            conversation.id = dataSnapshot.key
                            subscriber.onNext(ConversationResponse.ConversationRemoved(conversation))
                        }
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        subscriber.onError(error?.toException())
                    }

                })

    }

    override fun createConversation(query: ConversationsQuery.CreateConversation): Observable<Conversation> = retrofit.create(FirebaseAPI::class.java)
            .createConversation(query.conversation)
            .map { setIdToConversation(query.conversation, it.name) }

    private fun setIdToConversation(conversation: Conversation, id: String): Conversation {
        conversation.id = id
        return conversation
    }

}