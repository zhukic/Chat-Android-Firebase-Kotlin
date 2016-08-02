package com.rus.chat.repositories.chat.datasource.mapper

import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.entity.session.User
import org.joda.time.DateTime

/**
 * Created by RUS on 02.08.2016.
 */
class MessageMapper {

    companion object {

        fun transformFromMessageEntityAndUser(messageEntity: MessageEntity, user: User, isMessageMine: Boolean): MessageModel
                = MessageModel(messageEntity.id, messageEntity.text, user.name, DateTime.parse(messageEntity.time), isMessageMine)

    }

}