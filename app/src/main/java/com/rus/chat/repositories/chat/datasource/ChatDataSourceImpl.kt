package com.rus.chat.repositories.chat.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.response.MessageResponse
import com.rus.chat.net.FirebaseAPI
import retrofit2.Retrofit
import rx.Observable
import java.util.*

/**
 * Created by RUS on 23.07.2016.
 */
class ChatDataSourceImpl(val retrofit: Retrofit, val firebaseDatabase: FirebaseDatabase, val firebaseAuth: FirebaseAuth) : ChatDataSource {

    override fun getChatMessages(query: ChatQuery.GetChatMessages): Observable<MessageResponse.Response> = Observable.create { subscriber ->
        firebaseDatabase.reference
                .child("messages")
                .orderByChild("conversationId")
                .equalTo(query.conversationId)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(Message::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(MessageResponse.MessageChanged(message))
                        }
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(Message::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(MessageResponse.MessageAdded(message))
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(Message::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(MessageResponse.MessageRemoved(message))
                        }
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        subscriber.onError(error?.toException())
                    }
                })
    }

    override fun sendMessage(query: ChatQuery.SendMessage): Observable<FirebaseResponse> {
        val message = query.message
        message.userId = firebaseAuth.currentUser?.uid.toString()
        return retrofit.create(FirebaseAPI::class.java).sendMessage(message)
                .doOnNext { response -> query.message.id = response.id }
                .doOnNext { updateConversation(query.message) }
    }

    private fun updateConversation(message: Message) {
        val map = mutableMapOf<String, Any>("lastMessageId" to message.id)
        firebaseDatabase.reference
                .child("conversations")
                .child(message.conversationId)
                .updateChildren(map)
    }

}