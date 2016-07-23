package com.rus.chat.presenters.chat

import com.rus.chat.ui.chat.ChatView

/**
 * Created by RUS on 23.07.2016.
 */
interface ChatPresenter {

    fun attachView(chatView: ChatView)

    fun initialize()

    fun sendMessage()

    fun onDestroy()

}