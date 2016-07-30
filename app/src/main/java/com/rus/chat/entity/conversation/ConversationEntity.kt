package com.rus.chat.entity.conversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationEntity(id: String = "",
                         name: String = "",
                         @SerializedName("lastMessageId") @Expose val lastMessageId: String = "",
                         @SerializedName("lastMessageUserId") @Expose val lastMessageUserId: String = "") : BaseConversation(id, name), Serializable