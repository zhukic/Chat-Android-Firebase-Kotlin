package com.rus.chat.di

import android.content.Context
import com.rus.chat.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RUS on 14.07.2016.
 */
@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context = app

}