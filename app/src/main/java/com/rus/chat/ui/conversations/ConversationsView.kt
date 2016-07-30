package com.rus.chat.ui.conversations

import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.ui.BaseView

/**
 * Created by RUS on 17.07.2016.
 */
interface ConversationsView : BaseView {

    fun setConversations(conversationEntities: List<ConversationModel>?)

    fun showCreateConversationFragment()

    fun addConversation(conversationEntity: ConversationModel)

    fun changeConversation(conversationEntity: ConversationModel)

    fun removeConversation(conversationEntity: ConversationModel)

    fun openChatActivity(chatId: String)

    fun showProgress()

    fun hideProgress()

}