package com.rus.chat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rus.chat.R
import com.rus.chat.entity.chat.Message
import com.rus.chat.entity.conversation.Conversation
import com.rus.chat.utils.Logger
import kotlinx.android.synthetic.main.conversation_item.view.*
import kotlinx.android.synthetic.main.message_item.view.*

/**
 * Created by RUS on 24.07.2016.
 */
class ChatAdapter(val onItemClickListener: OnItemClickListener, val items: MutableList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(item: Message)
        fun onLongItemClicked(item: Message)
    }

    inner class ItemViewHolder(val v: View, val messageText: TextView = v.message_text) : RecyclerView.ViewHolder(v) {

        fun bind(message: Message) {
            this.messageText.text = message.text
            this.itemView.setOnClickListener { onItemClickListener.onItemClicked(message) }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as ItemViewHolder).bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
            = ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.message_item, parent, false))

    override fun getItemCount(): Int = items.size

}