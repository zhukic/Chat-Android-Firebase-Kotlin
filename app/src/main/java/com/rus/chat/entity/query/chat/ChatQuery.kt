package com.rus.chat.entity.query.chat

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 24.07.2016.
 */
class ChatQuery {

    data class GetConversationMessages(val conversationId: String) : Query

    data class SendMessage(val conversationId: String, val text: String, val time: String) : Query

    interface Query : BaseQuery

}