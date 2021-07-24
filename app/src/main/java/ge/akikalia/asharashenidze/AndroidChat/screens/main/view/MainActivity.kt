package ge.akikalia.asharashenidze.AndroidChat.screens.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation

class MainActivity : AppCompatActivity() {
    private lateinit var conversationListRecyclerView: RecyclerView
    private val conversationListAdapter = ConversationListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        updateList(listOf(Conversation("Jon Doe", "", ""),
                            Conversation("Jane Doe", "", ""),
                            Conversation("John Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),
            Conversation("Jon Doe", "", ""),
            Conversation("Jane Doe", "", ""),))
    }

    fun initViews(){
        conversationListRecyclerView = findViewById(R.id.conversationList)
        conversationListRecyclerView.layoutManager = LinearLayoutManager(this)
        conversationListRecyclerView.adapter = conversationListAdapter
    }

    private fun updateList(list: List<Conversation>){
        conversationListAdapter.list = list
        conversationListAdapter.notifyDataSetChanged()
    }
    
}