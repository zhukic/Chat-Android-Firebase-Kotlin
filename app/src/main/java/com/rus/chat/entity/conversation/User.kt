package com.rus.chat.entity.conversation

import com.google.gson.annotations.SerializedName

/**
 * Created by RUS on 17.07.2016.
 */
class User {
    @SerializedName("name")
    lateinit var name: String

    @SerializedName("id")
    lateinit var id: String

    constructor() {

    }

    override fun toString(): String {
        return "$id $name"
    }
}