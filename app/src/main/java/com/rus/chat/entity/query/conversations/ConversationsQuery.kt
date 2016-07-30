package com.rus.chat.entity.query.conversations

import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 19.07.2016.
 */
class ConversationsQuery {

    class GetConversations : Query

    class CreateConversation(val conversationEntity: ConversationEntity) : Query

    class Initialize : Query

    interface Query: BaseQuery

}