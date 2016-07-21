package com.rus.chat.entity.conversation

import com.google.gson.annotations.SerializedName

/**
 * Created by RUS on 17.07.2016.
 */
data class Conversation(@SerializedName("id") var id: String = "",
                        @SerializedName("name") val name: String = "",
                        @SerializedName("lastMessage") var lastMessage: String = "",
                        @SerializedName("lastMessageTime") var lastMessageTime: String = "",
                        @SerializedName("countOfUnreadMessages") var countOfUnreadMessages: Int = 0)