package com.rus.chat.ui.conversations

import com.rus.chat.entity.conversation.Conversation

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsView {

    fun setConversations(conversations: List<Conversation>)

    fun showProgress()

    fun hideProgress()

    fun onError(throwable: Throwable?)

    fun showSnackbar(message: String?)

}