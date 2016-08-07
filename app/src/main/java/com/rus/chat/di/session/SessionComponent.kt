package com.rus.chat.di.session

import com.rus.chat.interactors.session.SessionUseCase
import com.rus.chat.repositories.BaseRepository
import com.rus.chat.repositories.session.SessionRepository
import com.rus.chat.repositories.session.datasource.SessionDataSourceImpl
import com.rus.chat.ui.login.LoginActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Subcomponent(modules = arrayOf(SessionModule::class))
@SessionScope
interface SessionComponent {

    fun inject(loginActivity: LoginActivity)

}