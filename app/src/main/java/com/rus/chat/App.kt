package com.rus.chat

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.rus.chat.di.AppComponent
import com.rus.chat.di.AppModule
import com.rus.chat.di.net.DaggerNetComponent
import com.rus.chat.di.net.NetComponent
import com.rus.chat.di.net.NetModule
import com.rus.chat.di.session.DaggerSessionComponent
import com.rus.chat.di.session.SessionComponent
import com.rus.chat.di.session.SessionModule
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.utils.HandleUtils
import com.rus.chat.utils.Logger

/**
 * Created by RUS on 12.07.2016.
 */
class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var sessionComponent: SessionComponent
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        createAppComponent()
        createNetComponent()
        createSessionRepository()

    }

    private fun createSessionRepository() {
        val sessionRepository = SessionRepository()

        HandleUtils.registerHandlers(sessionRepository, SessionDataSourceImpl())

        createSessionComponent(sessionRepository)
    }

    private fun createAppComponent() {
//        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .build()
    }

    private fun createSessionComponent(sessionRepository: SessionRepository) {
        sessionComponent = DaggerSessionComponent.builder()
                .sessionModule(SessionModule(sessionRepository))
                .build()
    }

    private fun createNetComponent() {
        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule())
                .build()
    }

}