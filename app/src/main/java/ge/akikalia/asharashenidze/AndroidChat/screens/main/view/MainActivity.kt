package ge.akikalia.asharashenidze.AndroidChat.screens.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter.MainPresenter

class MainActivity : AppCompatActivity() {
    private lateinit var conversationListRecyclerView: RecyclerView
    private val conversationListAdapter = ConversationListAdapter()

    val presenter = MainPresenter()
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout : TabLayout
    private var fragment1 = MainChatListFragment()
    private var fragment2 = ProfileFragment()

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