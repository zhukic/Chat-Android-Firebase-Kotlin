package com.rus.chat.entity.chat

import com.google.gson.annotations.SerializedName
import com.rus.chat.entity.conversation.Conversation

/**
 * Created by RUS on 23.07.2016.
 */
data class Message(var messageId: String = "",
                   @SerializedName("conversationId") val conversationId: String = "",
                   @SerializedName("userID") var userId: String = "",
                   @SerializedName("text") val text: String = "",
                   @SerializedName("time") val time: String = "")