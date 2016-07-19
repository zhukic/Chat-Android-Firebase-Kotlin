package com.rus.chat.entity.conversation

import com.google.gson.annotations.SerializedName

/**
 * Created by RUS on 17.07.2016.
 */
data class Conversation(@SerializedName("id") val id: String,
                        @SerializedName("name") val name: String,
                        @SerializedName("lastMessage") val lastMessage: String,
                        @SerializedName("countOfUnreadMessages") val countOfUnreadMessages: Int)