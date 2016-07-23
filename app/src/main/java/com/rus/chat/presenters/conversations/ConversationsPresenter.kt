package com.rus.chat.presenters.conversations

import com.rus.chat.presenters.BasePresenter
import com.rus.chat.ui.conversations.ConversationsView

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsPresenter : BasePresenter {

    fun attachView(conversationsView: ConversationsView)

    fun initialize()

    fun onCreateConversationButtonClicked()

    fun createConversation(conversationName: String)

}