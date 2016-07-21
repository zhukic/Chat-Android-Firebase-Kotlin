package com.rus.chat.di.session

import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Module(includes = arrayOf(FirebaseModule::class, NetModule::class))
class SessionModule() {

    @Provides
    @Singleton
    fun getSessionRepository(sessionDataSourceImpl: SessionDataSourceImpl): BaseRepository = SessionRepository(sessionDataSourceImpl)

    @Provides
    @Singleton
    fun getSessionDataSourceImpl(retrofit: Retrofit, firebaseAuth: FirebaseAuth): SessionDataSourceImpl = SessionDataSourceImpl(retrofit, firebaseAuth)

}