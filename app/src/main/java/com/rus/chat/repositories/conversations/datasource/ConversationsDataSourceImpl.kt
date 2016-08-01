package com.rus.chat.repositories.conversations.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.rus.chat.App
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.BaseConversation
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.repositories.conversations.datasource.mapper.ConversationMapper
import com.rus.chat.entity.session.User
import com.rus.chat.entity.query.BaseQuery
import com.rus.chat.entity.query.conversations.ConversationsQuery
import com.rus.chat.entity.response.BaseResponse
import com.rus.chat.entity.response.ConversationResponse
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.net.FirebaseAPI
import com.rus.chat.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.functions.Func2
import rx.lang.kotlin.subscriber
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsDataSourceImpl(val retrofit: Retrofit, val firebaseDatabase: FirebaseDatabase) : ConversationsDataSource {

    override fun initialize(query: ConversationsQuery.GetConversations): Observable<ConversationResponse> = Observable.create<ConversationResponse> { subscriber ->
        firebaseDatabase.reference
                .child("conversations")
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(ConversationEntity::class.java)
                            conversation.id = dataSnapshot.key
                            Logger.log("changed ${conversation.name}")
                            subscriber.onNext(ConversationResponse(conversation, ConversationResponse.Type.CHANGED))
                        }
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot?, previousChildName: String?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(ConversationEntity::class.java)
                            conversation.id = dataSnapshot.key
                            Logger.log("added ${conversation.name}")
                            subscriber.onNext(ConversationResponse(conversation, ConversationResponse.Type.ADDED))
                        }
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                        if(dataSnapshot != null) {
                            val conversation = dataSnapshot.getValue(ConversationEntity::class.java)
                            conversation.id = dataSnapshot.key
                            subscriber.onNext(ConversationResponse(conversation, ConversationResponse.Type.REMOVED))
                        }
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        subscriber.onError(error?.toException())
                    }

                })
    }.flatMap { response -> convertToConversationModel(response)  }
    
    override fun createConversation(query: ConversationsQuery.CreateConversation): Observable<FirebaseResponse> = retrofit.create(FirebaseAPI::class.java)
            .createConversation(ConversationEntity(name = query.conversationName))

    private fun convertToConversationModel(response: ConversationResponse): Observable<ConversationResponse> {
        return getMessage((response.conversation as ConversationEntity).lastMessageId).concatMap { message ->
            Observable.zip(Observable.just(message), getUserById(message?.userId), { message, user ->
                response.conversation = ConversationMapper.createConversationWithMessageAndUser(response.conversation, message, user)
                response
            })
        }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }

    private fun setIdToConversation(conversationEntity: ConversationEntity, id: String): ConversationEntity {
        conversationEntity.id = id
        return conversationEntity
    }
    
    private fun getMessage(messageId: String?): Observable<Message> = retrofit.create(FirebaseAPI::class.java).getMessageById(messageId ?: "")
    
    private fun getUserById(userId: String?): Observable<User> = retrofit.create(FirebaseAPI::class.java).getUserById(userId ?: "")

}