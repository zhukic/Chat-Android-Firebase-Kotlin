package com.rus.chat.entity.conversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationEntity(id: String = "",
                         @SerializedName("name") @Expose override var name: String = "",
                         @SerializedName("lastMessageId") @Expose var lastMessageId: String? = null) : BaseConversation(id, name), Serializable