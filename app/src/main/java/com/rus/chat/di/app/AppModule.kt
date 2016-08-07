package com.rus.chat.di.app

import android.content.Context
import com.rus.chat.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RUS on 03.08.2016.
 */
@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = app

}