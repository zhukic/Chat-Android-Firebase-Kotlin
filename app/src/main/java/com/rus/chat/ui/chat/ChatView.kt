package com.rus.chat.ui.chat

import com.rus.chat.entity.chat.Message
import com.rus.chat.ui.BaseView

/**
 * Created by RUS on 23.07.2016.
 */
interface ChatView : BaseView {

    fun setMessages(messages: List<Message>)

    fun addMessage(message: Message)

    fun changeMessage(message: Message)

    fun removeMessage(message: Message)

}