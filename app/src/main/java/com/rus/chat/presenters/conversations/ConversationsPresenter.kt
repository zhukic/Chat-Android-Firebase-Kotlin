package com.rus.chat.presenters.conversations

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsPresenter {

    fun initialize()

    fun onCreateConversationButtonClicked()

    fun createConversation(conversationName: String)

    fun onDestroy()

}