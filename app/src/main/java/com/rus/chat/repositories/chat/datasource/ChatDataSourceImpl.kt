package com.rus.chat.repositories.chat.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.entity.response.ResponseType
import com.rus.chat.entity.query.chat.ChatQuery
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.net.FirebaseAPI
import com.rus.chat.repositories.chat.datasource.mapper.MessageMapper
import retrofit2.Retrofit
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by RUS on 23.07.2016.
 */
class ChatDataSourceImpl(val retrofit: Retrofit, val firebaseDatabase: FirebaseDatabase, val firebaseAuth: FirebaseAuth) : ChatDataSource {

    override fun getChatMessages(query: ChatQuery.GetConversationMessages): Observable<Pair<MessageModel, ResponseType>> = Observable.create<Pair<MessageEntity, ResponseType>> { subscriber ->
        firebaseDatabase.reference
                .child("messages")
                .orderByChild("conversationId")
                .equalTo(query.conversationId)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(dataSnapshot: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(MessageEntity::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(message to ResponseType.CHANGED)
                        }
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(MessageEntity::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(message to ResponseType.ADDED)
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                        if(dataSnapshot != null) {
                            val message = dataSnapshot.getValue(MessageEntity::class.java)
                            message.id = dataSnapshot.key
                            subscriber.onNext(message to ResponseType.REMOVED)
                        }
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        subscriber.onError(error?.toException())
                    }
                })
    }
            .flatMap { pair -> Observable.zip(Observable.just(pair), getUserById(pair.first.userId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()),
                    { pair, user -> MessageMapper.transformFromMessageEntityAndUser(pair.first, user, user.uid.equals(firebaseAuth.currentUser?.uid)) to pair.second } ) }

    override fun sendMessage(query: ChatQuery.SendMessage): Observable<FirebaseResponse> {
        val message = MessageEntity(conversationId = query.conversationId, text = query.text, time = query.time)
        message.userId = firebaseAuth.currentUser?.uid.toString()
        return retrofit.create(FirebaseAPI::class.java).sendMessage(message)
                .doOnNext { response -> message.id = response.id }
                .doOnNext { updateConversation(message) }
    }

    private fun getUserById(userId: String) = retrofit.create(FirebaseAPI::class.java).getUserById(userId)

    private fun updateConversation(messageEntity: MessageEntity) {
        val map = mutableMapOf<String, Any>("lastMessageId" to messageEntity.id)
        firebaseDatabase.reference
                .child("conversations")
                .child(messageEntity.conversationId)
                .updateChildren(map)
    }

}