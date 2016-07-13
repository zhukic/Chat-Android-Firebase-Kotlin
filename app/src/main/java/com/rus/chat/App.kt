package com.rus.chat

import android.app.Application
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.utils.HandleUtils

/**
 * Created by RUS on 12.07.2016.
 */
class App : Application() {

    companion object {
        val sessionRepository: SessionRepository = SessionRepository()
    }

    override fun onCreate() {
        super.onCreate()
        HandleUtils.registerHandlers(sessionRepository, SessionDataSourceImpl())
    }

}