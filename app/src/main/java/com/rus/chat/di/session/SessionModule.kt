package com.rus.chat.di.session

import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.di.firebase.FirebaseModule
import com.rus.chat.di.net.NetModule
import com.rus.chat.interactors.session.*
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.repositories.session.datasource.SessionDataSourceImpl
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
    fun getSignIn(sessionRepository: SessionRepository): SignIn = SignIn(sessionRepository)

    @Provides
    @Singleton
    fun getSignOut(sessionRepository: SessionRepository): SignOut = SignOut(sessionRepository)

    @Provides
    @Singleton
    fun getRegister(sessionRepository: SessionRepository): Register = Register(sessionRepository)

    @Provides
    @Singleton
    fun getCurrentUser(sessionRepository: SessionRepository): GetCurrentUser = GetCurrentUser(sessionRepository)

    @Provides
    @Singleton
    fun getSessionRepository(sessionDataSourceImpl: SessionDataSourceImpl): SessionRepository = SessionRepository(sessionDataSourceImpl)

    @Provides
    @Singleton
    fun getSessionDataSourceImpl(retrofit: Retrofit, firebaseAuth: FirebaseAuth): SessionDataSourceImpl = SessionDataSourceImpl(retrofit, firebaseAuth)

}