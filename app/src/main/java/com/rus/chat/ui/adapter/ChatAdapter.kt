package com.rus.chat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rus.chat.R
import com.rus.chat.entity.chat.MessageEntity
import com.rus.chat.entity.chat.MessageModel
import com.rus.chat.entity.conversation.ConversationEntity
import com.rus.chat.utils.Logger
import kotlinx.android.synthetic.main.conversation_item.view.*
import kotlinx.android.synthetic.main.message_item.view.*

/**
 * Created by RUS on 24.07.2016.
 */
class ChatAdapter(val onItemClickListener: OnItemClickListener, val items: MutableList<MessageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(item: MessageModel)
        fun onLongItemClicked(item: MessageModel)
    }

    inner class ItemViewHolder(val v: View,
                               val messageText: TextView = v.message_text,
                               val messageUserAndTime: TextView = v.message_user_and_time) : RecyclerView.ViewHolder(v) {

        fun bind(messageModel: MessageModel) {
            this.messageText.text = messageModel.text
            this.messageUserAndTime.text = "${messageModel.userName}, ${messageModel.time.toString(PATTERN)}"

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.END

            if(messageModel.isMine)
                params.gravity = Gravity.END
            else
                params.gravity = Gravity.START
            this.messageText.layoutParams = params

            this.itemView.setOnClickListener { onItemClickListener.onItemClicked(messageModel) }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as ItemViewHolder).bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder?
            = ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.message_item, parent, false))

    override fun getItemCount(): Int = items.size

    companion object {
        val PATTERN: String = "dd/MM/yyyy HH:mm"
    }

}