package com.rus.chat.entity.response

import com.rus.chat.entity.conversation.BaseConversation
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel

/**
 * Created by RUS on 21.07.2016.
 */
data class ConversationResponse(var conversation: BaseConversation, val type: Type) {
    enum class Type {
        ADDED,
        CHANGED,
        REMOVED
    }
}