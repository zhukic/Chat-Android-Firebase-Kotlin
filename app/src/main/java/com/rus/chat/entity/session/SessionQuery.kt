package com.rus.chat.entity.session

/**
 * Created by RUS on 12.07.2016.
 */
class SessionQuery {

    data class SignIn(val email: String, val password: String) : Query

    data class Register(val email: String, val password: String) : Query

    class SignOut : Query

}