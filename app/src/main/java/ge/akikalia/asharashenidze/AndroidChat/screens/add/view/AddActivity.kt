package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.UserInfo
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import ge.akikalia.asharashenidze.AndroidChat.screens.add.presenter.AddPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity

class AddActivity : AppCompatActivity(), IAddView {
    private lateinit var userListRecyclerView: RecyclerView
    private val userListAdapter = UserListAdapter(this)
    private lateinit var searchBar: TextInputEditText
    lateinit var userDataList: List<UserData>

    val presenter = AddPresenter(this)

//    todo: need to call presenter search when client searches for users
//    todo: need to call finish when back button is pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initViews()
        initListeners()
        presenter.onViewLoad()
    }

    private fun initListeners() {
        this.findViewById<TextInputEditText>(R.id.add_search_input).addTextChangedListener {
            updateAdapterList(userDataList)
        }
    }

    fun initViews(){
        searchBar = findViewById(R.id.add_search_input)
        userListRecyclerView = findViewById(R.id.userList)
        userListRecyclerView.layoutManager = LinearLayoutManager(this)
        userListRecyclerView.adapter = userListAdapter
        findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            finish()
        }
    }

    override fun listItemClickedWithId(id: String) {
        val intent = Intent(this, ChatActivity::class.java).apply {
            putExtra(ChatActivity.CHAT_ID, id)
        }
        startActivity(intent)
    }

    override fun updateList(list: List<UserData>) {
        userDataList = list
        updateAdapterList(userDataList)
    }

    private fun updateAdapterList(list: List<UserData>) {
        userListAdapter.list = list.
        filter{userData -> userData.username.contains(searchBar.text.toString()) }
        userListAdapter.notifyDataSetChanged()
    }

    override fun closeView() {
        finish()
    }

    override fun startLoader() {
        //todo: need to implement
    }

    override fun stopLoader(error: Boolean) {
        //todo: need to implement
    }

    override fun addUser(id: String){
        presenter.addUser(id)
    }
}