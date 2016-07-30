package com.rus.chat.entity.mapper

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.BaseConversation
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.entity.session.User

/**
 * Created by RUS on 30.07.2016.
 */
class ConversationMapper {

    companion object {

        fun createConversationWithMessageAndUser(baseConversation: BaseConversation, message: Message?, user: User?): ConversationModel {
            val conversationModel = ConversationModel()
            conversationModel.id = baseConversation.id
            conversationModel.name = baseConversation.name
            if(message != null) {
                conversationModel.lastMessage = message.text
                conversationModel.lastMessageTime = message.time
            }
            if(user != null) {
                conversationModel.lastMessageUser = user.name
            }
            return conversationModel
        }

    }

}