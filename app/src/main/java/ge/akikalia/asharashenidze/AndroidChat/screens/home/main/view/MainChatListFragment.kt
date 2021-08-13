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
<<<<<<< HEAD:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/main/view/MainActivity.kt
import ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter.IMainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter.MainPresenter

class MainActivity : AppCompatActivity(), IMainView {
    private lateinit var conversationListRecyclerView: RecyclerView
    private val conversationListAdapter = ConversationListAdapter()

    val presenter = MainPresenter(this)
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout : TabLayout
    private var fragment1 = MainChatListFragment()
    private var fragment2 = ProfileFragment()
=======
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter.MainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.ProfileFragment

class MainChatListFragment : Fragment() {
    private lateinit var conversationListRecyclerView: RecyclerView
    private val conversationListAdapter = ConversationListAdapter()

    val presenter = MainPresenter()
    private lateinit var mainView: View
>>>>>>> b4b0607eb34d449ae54469f35c7533be6386eb10:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/home/main/view/MainChatListFragment.kt

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainView = view
        initViews()
<<<<<<< HEAD:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/main/view/MainActivity.kt
=======
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
>>>>>>> b4b0607eb34d449ae54469f35c7533be6386eb10:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/home/main/view/MainChatListFragment.kt
    }


    fun initViews(){
        conversationListRecyclerView = mainView.findViewById(R.id.conversationList)
        conversationListRecyclerView.layoutManager = LinearLayoutManager(mainView.context)
        conversationListRecyclerView.adapter = conversationListAdapter
    }

    override fun updateConversations(list: List<Conversation>) {
        conversationListAdapter.list = list
        conversationListAdapter.notifyDataSetChanged()
    }

<<<<<<< HEAD:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/main/view/MainActivity.kt
    override fun displayError() {
        TODO("Not yet implemented")
    }

=======
>>>>>>> b4b0607eb34d449ae54469f35c7533be6386eb10:app/src/main/java/ge/akikalia/asharashenidze/AndroidChat/screens/home/main/view/MainChatListFragment.kt
}