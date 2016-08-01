package com.rus.chat.repositories.conversations.datasource.mapper

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.BaseConversation
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.entity.session.User
import com.rus.chat.utils.isToday
import com.rus.chat.utils.isYesterday
import org.joda.time.DateTime

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
                if(!message.time.equals("")) {
                    val dateTime = DateTime.parse(message.time)
                    if(dateTime.isToday())
                        conversationModel.lastMessageTime = dateTime.toString("HH:mm")
                    else if(dateTime.isYesterday())
                        conversationModel.lastMessageTime = "Вчера"
                    else conversationModel.lastMessageTime = dateTime.toString("dd MMM")
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