package com.rus.chat.repositories.conversations.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rus.chat.App
import com.rus.chat.entity.conversation.User
import com.rus.chat.repositories.FirebaseAPI
import com.rus.chat.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import rx.Observable
import javax.inject.Inject

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsDataSourceImpl : ConversationsDataSource {


    var retrofit: Retrofit = null!!

    init {
        /*App.appComponent.inject(this)
        Logger.log(FirebaseAuth.getInstance().currentUser?.email)
        retrofit.create(FirebaseAPI::class.java).addUser(FirebaseAuth.getInstance().currentUser!!.uid, User("123456", "zhukic")).enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Logger.log("error " + t?.message)
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                Logger.log("response")
            }
        })
        //FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(User("12345", "zhukic"))*/
    }

    override fun getConversations(): Observable<User> = retrofit.create(FirebaseAPI::class.java).getConversations(FirebaseAuth.getInstance().currentUser!!.uid)

}