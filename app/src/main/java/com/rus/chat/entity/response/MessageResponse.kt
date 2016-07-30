package com.rus.chat.entity.response

import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.ConversationEntity

/**
 * Created by RUS on 26.07.2016.
 */
class MessageResponse {

    class MessageAdded(body: Message) : BaseResponse<Message>(body), Response
    class MessageChanged(body: Message) : BaseResponse<Message>(body), Response
    class MessageRemoved(body: Message) : BaseResponse<Message>(body), Response

    interface Response
}