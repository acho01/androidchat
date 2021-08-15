package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.dto.ChatPreviewDto


class ChatPreviewListAdapter(val view: IMainChatListView): RecyclerView.Adapter<ChatPreviewListAdapter.ChatPreviewViewHolder>() {

    var list = listOf<ChatPreviewDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPreviewViewHolder {
        return ChatPreviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_preview_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: ChatPreviewViewHolder, position: Int) {
        val packet = list[position]
        holder.itemView.setOnClickListener {
            view.listItemClickedWithId(packet.id, packet.username)
        }
        holder.bindPacket(packet)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ChatPreviewViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindPacket(chatPreview: ChatPreviewDto) {
            name.text = chatPreview.username
            timePassed.text = chatPreview.timestamp.toString()
            lastMessage.text = chatPreview.lastMessage
        }

        private val name = view.findViewById<TextView>(R.id.chat_preview_list_item_name)
        private val timePassed = view.findViewById<TextView>(R.id.chat_preview_list_item_time_passed)
        private val lastMessage = view.findViewById<TextView>(R.id.chat_preview_list_item_summary)
    }
}