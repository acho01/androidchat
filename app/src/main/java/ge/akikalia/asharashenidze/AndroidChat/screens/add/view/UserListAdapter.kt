package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.model.User

class UserListAdapter(): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var list = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val packet = list[position]
        holder.bindPacket(packet)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindPacket(User: User) {
            name.text = User.username
            occupation.text = User.occupation
        }

        private val name = view.findViewById<TextView>(R.id.userListItemName)
        private val occupation = view.findViewById<TextView>(R.id.userListItemSummary)
    }
}