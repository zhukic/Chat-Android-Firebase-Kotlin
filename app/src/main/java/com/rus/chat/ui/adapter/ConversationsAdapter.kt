package com.rus.chat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rus.chat.R
import com.rus.chat.entity.conversation.Conversation

/**
 * Created by RUS on 17.07.2016.
 */
class ConversationsAdapter(val onItemClickListener: OnItemClickListener, val items: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
        fun onLongItemClicked(position: Int)
    }

    class ItemViewHolder(
            val v: View ) : RecyclerView.ViewHolder(v)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
            = ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.conversation_item, parent, false))

    override fun getItemCount(): Int = items.size

}