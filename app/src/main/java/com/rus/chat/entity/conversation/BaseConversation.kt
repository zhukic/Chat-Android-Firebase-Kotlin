package com.rus.chat.entity.conversation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by RUS on 30.07.2016.
 */
open abstract class BaseConversation(var id: String, open var name: String) : Serializable