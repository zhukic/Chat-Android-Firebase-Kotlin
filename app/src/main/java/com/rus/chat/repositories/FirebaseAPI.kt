package com.rus.chat.repositories

import com.rus.chat.entity.conversation.User
import retrofit2.Call
import retrofit2.http.*
import rx.Observable

/**
 * Created by RUS on 17.07.2016.
 */
interface FirebaseAPI {

    @GET("users/{id}.json")
    fun getConversations(@Path("id") id: String): Observable<User>

    @PUT("users/{id}.json")
    fun addUser(@Path("id") id: String, @Body user: User): Observable<User>

    @GET("users/{id}.json")
    fun getUserById(@Path("id") uid: String): Observable<User>

}