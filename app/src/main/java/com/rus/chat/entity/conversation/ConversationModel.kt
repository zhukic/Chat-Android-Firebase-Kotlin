package com.rus.chat.entity.conversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by RUS on 30.07.2016.
 */
class ConversationModel(id: String = "",
                        name: String = "",
                        var lastMessage: String = "",
                        var lastMessageTime: String = "",
                        var lastMessageUser: String = "") : BaseConversation(id, name), Serializable