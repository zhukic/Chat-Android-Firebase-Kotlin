package com.rus.chat.entity.response

import com.rus.chat.entity.conversation.Conversation

/**
 * Created by RUS on 21.07.2016.
 */
class ConversationResponse {

    class ConversationAdded(body: Conversation) : BaseResponse<Conversation>(body), Response
    class ConversationChanged(body: Conversation) : BaseResponse<Conversation>(body), Response
    class ConversationRemoved(body: Conversation) : BaseResponse<Conversation>(body), Response

    interface Response
}