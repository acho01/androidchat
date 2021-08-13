package ge.akikalia.asharashenidze.AndroidChat.screens.chat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.MessageDto


class ChatAdapter(var context: Context, var messageDtoList: List<MessageDto>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    override fun getItemViewType(position: Int): Int {
        val messageDto: MessageDto = messageDtoList.get(position)
        //TODO[A.SH] refactor it to logged in user
        return if (messageDto.sender.nickname.equals("acho01")) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context).inflate(ge.akikalia.asharashenidze.AndroidChat.R.layout.chat_list_item_me, parent, false)
            return SentMessageHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(ge.akikalia.asharashenidze.AndroidChat.R.layout.chat_list_item_other, parent, false)
            return ReceivedMessageHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val messageDto: MessageDto = messageDtoList.get(position)

        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder).bind(messageDto)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(messageDto)
        }
    }

    override fun getItemCount(): Int {
        return messageDtoList.size
    }


    inner class SentMessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var messageText =
            view.findViewById<TextView>(ge.akikalia.asharashenidze.AndroidChat.R.id.text_chat_message_me)
        var timeText =
            view.findViewById<TextView>(ge.akikalia.asharashenidze.AndroidChat.R.id.text_chat_time_me)

        fun bind(messageDto: MessageDto) {
            messageText.text = messageDto.message
            timeText.text = messageDto.time.toString()
        }
    }

    inner class ReceivedMessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var messageText =
            view.findViewById<TextView>(ge.akikalia.asharashenidze.AndroidChat.R.id.text_chat_message_other)
        var timeText =
            view.findViewById<TextView>(ge.akikalia.asharashenidze.AndroidChat.R.id.text_chat_time_other)
        var nameText =
            view.findViewById<TextView>(ge.akikalia.asharashenidze.AndroidChat.R.id.text_chat_user_other)

        fun bind(messageDto: MessageDto) {
            messageText.text = messageDto.message
            timeText.text = messageDto.time.toString()
            nameText.text = messageDto.sender.nickname
        }
    }

}