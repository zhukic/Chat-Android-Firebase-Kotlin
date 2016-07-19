package com.rus.chat.entity.query.session

import com.rus.chat.entity.conversation.User
import com.rus.chat.entity.query.BaseQuery

/**
 * Created by RUS on 12.07.2016.
 */
class SessionQuery {

    class GetCurrentUser : Query

    data class SignIn(val email: String, val password: String) : Query

    data class Register(val email: String,val name: String, val password: String) : Query

    class SignOut : Query

    interface Query : BaseQuery
}