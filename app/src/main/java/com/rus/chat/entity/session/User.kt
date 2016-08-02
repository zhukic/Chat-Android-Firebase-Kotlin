package com.rus.chat.entity.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(@SerializedName("uid") @Expose val uid: String,
                @SerializedName("name") @Expose val name: String = "") : Serializable
