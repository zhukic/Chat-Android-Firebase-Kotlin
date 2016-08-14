package com.rus.chat.entity.query.chat

import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 24.07.2016.
 */
sealed class ChatQuery : BaseQuery {
    class GetConversationMessages(val conversationId: String) : ChatQuery()
    class SendMessage(val conversationId: String, val text: String, val time: String) : ChatQuery()
}