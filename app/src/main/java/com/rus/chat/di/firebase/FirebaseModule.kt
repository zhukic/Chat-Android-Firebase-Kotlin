package com.rus.chat.di.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RUS on 21.07.2016.
 */
@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun getFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun getFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

}