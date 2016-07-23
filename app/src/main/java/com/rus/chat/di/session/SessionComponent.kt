package com.rus.chat.di.session

import com.rus.chat.interactors.session.SessionUseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.login.SessionRepository
import com.rus.chat.repositories.login.datasource.SessionDataSourceImpl
import com.rus.chat.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Component(modules = arrayOf(SessionModule::class))
@Singleton
interface SessionComponent {

    fun inject(loginActivity: LoginActivity)

    fun sessionUseCase() : SessionUseCase

}