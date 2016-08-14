package com.rus.chat.entity.query.conversations

import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 19.07.2016.
 */
sealed class ConversationsQuery : BaseQuery {
    class GetConversations : ConversationsQuery()
    class CreateConversation(val conversationName: String) : ConversationsQuery()
}