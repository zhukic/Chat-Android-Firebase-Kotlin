package com.rus.chat.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by RUS on 21.07.2016.
 */
data class FirebaseResponse(@SerializedName("name") @Expose val id: String)