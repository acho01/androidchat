package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.UserData

class UserListAdapter(private val addView: IAddView): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var list = listOf<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = list[position]
        holder.itemView.setOnClickListener {view ->
            userData.id.let{ id ->
                addView.addUser(id)
            }
        }
        holder.bindUserData(userData)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindUserData(user: UserData) {
            name.text = user.username
            occupation.text = user.occupation
        }

        private val name = view.findViewById<TextView>(R.id.userListItemName)
        private val occupation = view.findViewById<TextView>(R.id.userListItemSummary)
    }
}