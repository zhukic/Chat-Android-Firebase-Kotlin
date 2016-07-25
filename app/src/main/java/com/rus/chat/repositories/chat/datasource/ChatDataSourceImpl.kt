package com.rus.chat.repositories.chat.datasource

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.FirebaseResponse
import retrofit2.Retrofit
import rx.Observable
import java.util.*

/**
 * Created by RUS on 23.07.2016.
 */
class ChatDataSourceImpl(val retrofit: Retrofit, val firebaseDatabase: FirebaseDatabase) : ChatDataSource {

    override fun getChatMessages(query: ChatQuery.GetChatMessages): Observable<Message> = Observable.create { subscriber ->
        firebaseDatabase.reference
                .child("messages")
                .orderByValue()
                .startAt(query.conversationId)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(Message::class.java)
                            subscriber.onNext(message)
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        subscriber.onError(error?.toException())
                    }
                })
    }

    override fun sendMessage(query: ChatQuery.SendMessage): Observable<FirebaseResponse> = throw UnsupportedOperationException()
}