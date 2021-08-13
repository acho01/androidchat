package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.model.User
import ge.akikalia.asharashenidze.AndroidChat.screens.add.presenter.AddPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.add.view.IAddView
import ge.akikalia.asharashenidze.AndroidChat.screens.add.view.UserListAdapter
import ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter.MainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.ConversationListAdapter
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.IMainView
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.MainChatListFragment
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.ProfileFragment

class AddActivity : AppCompatActivity(), IAddView {
    private lateinit var userListRecyclerView: RecyclerView
    private val userListAdapter = UserListAdapter()
    private lateinit var searchBar: TextInputEditText

    val presenter = AddPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initViews()
        presenter.search("")
    }

    fun initViews(){
        searchBar = findViewById(R.id.search_bar)
        userListRecyclerView = findViewById(R.id.userList)
        userListRecyclerView.layoutManager = LinearLayoutManager(this)
        userListRecyclerView.adapter = userListAdapter
    }

    override fun updateList(list: List<User>) {
        userListAdapter.list = list
        userListAdapter.notifyDataSetChanged()
    }

    override fun displayError() {
        TODO("Not yet implemented")
    }

}