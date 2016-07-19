package com.rus.chat.di.session

import com.rus.chat.interactors.session.SessionUseCase
import dagger.Component
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Component(modules = arrayOf(SessionModule::class))
@Singleton
interface SessionComponent {

    fun inject(sessionUseCase: SessionUseCase)

}