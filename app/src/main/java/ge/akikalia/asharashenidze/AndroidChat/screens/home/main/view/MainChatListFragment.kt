package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter.MainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.ProfileFragment

class MainChatListFragment : Fragment() {
    private lateinit var conversationListRecyclerView: RecyclerView
    private val conversationListAdapter = ConversationListAdapter()

    val presenter = MainPresenter()
    private lateinit var mainView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainView = view
        initViews()
        updateList(listOf(
            Conversation("Jon Doe", "", ""),
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_chat_list, container, false)
    }


    fun initViews(){
        conversationListRecyclerView = mainView.findViewById(R.id.conversationList)
        conversationListRecyclerView.layoutManager = LinearLayoutManager(mainView.context)
        conversationListRecyclerView.adapter = conversationListAdapter
    }

    private fun updateList(list: List<Conversation>){
        conversationListAdapter.list = list
        conversationListAdapter.notifyDataSetChanged()
    }

}