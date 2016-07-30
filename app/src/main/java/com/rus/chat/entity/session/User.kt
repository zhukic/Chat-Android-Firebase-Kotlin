package com.rus.chat.entity.session

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(val uid: String, @SerializedName("name") val name: String = "") : Serializable
