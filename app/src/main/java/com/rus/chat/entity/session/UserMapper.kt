package com.rus.chat.entity.session

import com.google.firebase.auth.FirebaseUser

/**
 * Created by RUS on 26.07.2016.
 */
class UserMapper {

    companion object {
        fun transformFromFirebaseUser(firebaseUser: FirebaseUser?): User? {
            if(firebaseUser != null)
                return User(firebaseUser.uid, firebaseUser.email!!.split("@").first())
            else
                return null
        }
    }

}