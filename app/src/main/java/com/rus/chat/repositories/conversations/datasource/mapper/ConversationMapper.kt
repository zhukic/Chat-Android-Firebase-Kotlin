package com.rus.chat.repositories.conversations.datasource.mapper

import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.conversation.BaseConversation
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.entity.session.User
import com.rus.chat.utils.isToday
import com.rus.chat.utils.isYesterday
import com.rus.chat.utils.toChatTime
import org.joda.time.DateTime

/**
 * Created by RUS on 30.07.2016.
 */
class ConversationMapper {

    companion object {

        fun createConversationWithMessageAndUser(baseConversation: BaseConversation, messageEntity: MessageEntity?, user: User?): ConversationModel {
            val conversationModel = ConversationModel()
            conversationModel.id = baseConversation.id
            conversationModel.name = baseConversation.name
            if(messageEntity != null) {
                conversationModel.lastMessage = messageEntity.text
                if(!messageEntity.time.equals("")) {
                    conversationModel.lastMessageTime = DateTime.parse(messageEntity.time).toChatTime()
                }
            }
            if(user != null) {
                if(user.name != null)
                    conversationModel.lastMessageUser = user.name
            }
            return conversationModel
        }

        fun transformFromEntity(conversationEntity: ConversationEntity): ConversationModel {
            val conversationModel = ConversationModel()
            conversationModel.id = conversationEntity.id
            conversationModel.name = conversationEntity.name
            return conversationModel
        }

    }

}