package com.rus.chat.di.conversations

import com.rus.chat.repositories.BaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by RUS on 20.07.2016.
 */
@Module
class ConversationsModule(val baseRepository: BaseRepository) {

    @Provides
    @Singleton
    fun getSessionRepo(): BaseRepository = baseRepository

}