package com.rus.chat.ui.conversations

import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.ui.BaseView

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsView : BaseView {

    fun setConversations(conversations: List<Conversation>?)

    fun showCreateConversationFragment()

    fun addConversation(conversation: Conversation?)

    fun changeConversation(conversation: Conversation?)

    fun removeConversation(conversation: Conversation?)

    fun openChatActivity(chatId: String)

    fun showProgress()

    fun hideProgress()

}