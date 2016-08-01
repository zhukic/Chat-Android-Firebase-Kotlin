package com.rus.chat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rus.chat.R
import com.rus.chat.entity.conversation.ConversationModel
import com.rus.chat.utils.isToday
import com.rus.chat.utils.isYesterday
import kotlinx.android.synthetic.main.conversation_item.view.*
import org.joda.time.DateTime

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsAdapter(val onItemClickListener: OnItemClickListener, val items: MutableList<ConversationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(item: ConversationModel)
        fun onLongItemClicked(item: ConversationModel)
    }

    inner class ItemViewHolder(val v: View,
                               val conversationName: TextView = v.conversation_name,
                               val conversationLastMessageTime: TextView = v.conversation_last_message_time,
                               val conversationLastMessage: TextView = v.conversation_last_message,
                               val conversationLastMessageUser: TextView = v.conversation_last_message_user) : RecyclerView.ViewHolder(v) {

        fun bind(conversationModel: ConversationModel) {
            this.conversationLastMessage.text = conversationModel.lastMessage
            this.conversationName.text = conversationModel.name
            this.conversationLastMessageUser.text = conversationModel.lastMessageUser

            if(!conversationModel.lastMessageTime.equals("")) {
                val dateTime = DateTime.parse(conversationModel.lastMessageTime)
                if(dateTime.isToday())
                    this.conversationLastMessageTime.text = dateTime.toString("HH:mm")
                else if(dateTime.isYesterday())
                    this.conversationLastMessageTime.text = "Вчера"
                else this.conversationLastMessageTime.text = dateTime.toString("dd mmm")
            }

            this.itemView.setOnClickListener { onItemClickListener.onItemClicked(conversationModel) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as ItemViewHolder).bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
            = ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.conversation_item, parent, false))

    override fun getItemCount(): Int = items.size

}