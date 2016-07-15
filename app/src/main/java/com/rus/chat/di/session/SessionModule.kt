package com.rus.chat.di.session

import com.rus.chat.repositories.login.SessionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RUS on 14.07.2016.
 */
@Module
class SessionModule(val sessionRepository: SessionRepository) {

    @Provides
    @Singleton
    fun getSessionRepo(): SessionRepository = sessionRepository

}