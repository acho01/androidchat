package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.UserInfo
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import ge.akikalia.asharashenidze.AndroidChat.screens.add.presenter.AddPresenter

class AddActivity : AppCompatActivity(), IAddView {
    private lateinit var userListRecyclerView: RecyclerView
    private val userListAdapter = UserListAdapter(this)
    private lateinit var searchBar: TextInputEditText

    val presenter = AddPresenter(this)

//    todo: need to call presenter search when client searches for users
//    todo: need to call finish when back button is pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initViews()
        presenter.onViewLoad()
    }

    fun initViews(){
        searchBar = findViewById(R.id.search_bar)
        userListRecyclerView = findViewById(R.id.userList)
        userListRecyclerView.layoutManager = LinearLayoutManager(this)
        userListRecyclerView.adapter = userListAdapter
        findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            finish()
        }
    }

    override fun updateList(list: List<UserData>) {
        userListAdapter.list = list
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