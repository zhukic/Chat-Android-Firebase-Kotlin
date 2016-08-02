package com.rus.chat.ui.chat

import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.ui.BaseView

/**
 * Created by RUS on 23.07.2016.
 */
interface ChatView : BaseView {

    fun setMessages(messageEntities: List<MessageModel>)

    fun addMessage(messageEntity: MessageModel)

    fun changeMessage(messageEntity: MessageModel)

    fun removeMessage(messageEntity: MessageModel)

}