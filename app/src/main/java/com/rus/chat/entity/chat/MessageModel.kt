package com.rus.chat.entity.chat

import org.joda.time.DateTime

/**
 * Created by RUS on 02.08.2016.
 */
data class MessageModel(var id: String,
                        var text: String,
                        var userName: String,
                        var time: DateTime,
                        var isMine: Boolean)