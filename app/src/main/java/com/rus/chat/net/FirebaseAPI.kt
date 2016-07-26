package com.rus.chat.net

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.response.FirebaseResponse
import com.rus.chat.entity.conversation.User
import retrofit2.Call
import retrofit2.http.*
import rx.Observable

/**
 * Created by RUS on 17.07.2016.
 */
interface FirebaseAPI {

    @PUT("users/{id}.json")
    fun addUser(@Path("id") id: String, @Body user: User): Observable<User>

    @GET("users/{id}.json")
    fun getUserById(@Path("id") uid: String): Observable<User>

    @POST("conversations.json")
    fun createConversation(@Body conversation: Conversation): Observable<FirebaseResponse>

    @POST("messages.json")
    fun sendMessage(@Body message: Message): Observable<FirebaseResponse>

}