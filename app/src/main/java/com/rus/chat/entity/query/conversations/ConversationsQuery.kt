package com.rus.chat.entity.query.conversations

import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 19.07.2016.
 */
class ConversationsQuery {

    class GetConversations : Query

    class CreateConversation(val conversation: Conversation) : Query

    class Initialize : Query

    interface Query: BaseQuery

}