package com.rus.chat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rus.chat.R
import com.rus.chat.entity.conversation.Conversation
import kotlinx.android.synthetic.main.conversation_item.view.*

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsAdapter(val onItemClickListener: OnItemClickListener, val items: List<Conversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
        fun onLongItemClicked(position: Int)
    }

    class ItemViewHolder(val v: View,
                         val conversationName: TextView = v.conversation_name,
                         val conversationLastMessageTime: TextView = v.conversation_last_message_time,
                         val conversationLastMessage: TextView = v.conversation_last_message,
                         val conversationUnreadMessagesCount: TextView = v.conversation_unread_count) : RecyclerView.ViewHolder(v) {

        fun bind(conversation: Conversation) {
            this.conversationLastMessage.text = conversation.lastMessage
            this.conversationName.text = conversation.name
            this.conversationLastMessage.text = conversation.lastMessage
            this.conversationUnreadMessagesCount.text = conversation.countOfUnreadMessages.toString()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as ItemViewHolder).bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
            = ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.conversation_item, parent, false))

    override fun getItemCount(): Int = items.size

}