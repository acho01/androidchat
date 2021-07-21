package ge.akikalia.asharashenidze.AndroidChat.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation


class ConversationListAdapter(): RecyclerView.Adapter<ConversationListAdapter.ConversationViewHolder>() {

    var list = listOf<Conversation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        return ConversationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.conversation_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val packet = list[position]
        holder.bindPacket(packet)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ConversationViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindPacket(conversation: Conversation) {
            name.text = conversation.name
        }

        private val name = view.findViewById<TextView>(R.id.conversationListItemText)

    }
}