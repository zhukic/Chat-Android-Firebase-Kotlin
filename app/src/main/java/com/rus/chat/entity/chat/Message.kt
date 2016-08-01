package com.rus.chat.entity.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rus.chat.entity.conversation.ConversationEntity

/**
 * Created by RUS on 23.07.2016.
 */
data class Message(var id: String = "",
                   @SerializedName("conversationId") @Expose val conversationId: String = "",
                   @SerializedName("userId") @Expose var userId: String = "",
                   @SerializedName("text") @Expose val text: String = "",
                   @SerializedName("time") @Expose val time: String = "")