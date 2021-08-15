package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity.Companion.CHAT_ID
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter.MainChatListPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.LoginActivity

class MainChatListFragment : Fragment(), IMainChatListView {
    private lateinit var chatPreviewListRecyclerView: RecyclerView
    private val chatPreviewListAdapter = ChatPreviewListAdapter(this)

    val presenter = MainChatListPresenter(this)
    private lateinit var mainView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainView = view
        initViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_chat_list, container, false)
    }


    private fun initViews(){
        chatPreviewListRecyclerView = mainView.findViewById(R.id.chat_preview_list)
        chatPreviewListRecyclerView.layoutManager = LinearLayoutManager(mainView.context)
        chatPreviewListRecyclerView.adapter = chatPreviewListAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewDidLoad()
    }

    override fun updateList(list: List<ChatPreview>){
        chatPreviewListAdapter.list = list
        chatPreviewListAdapter.notifyDataSetChanged()
    }

    override fun listItemClickedWithId(id: String) {
        val intent = Intent(activity, ChatActivity::class.java).apply {
            putExtra(CHAT_ID, id)
        }
        startActivity(intent)
    }

    override fun startLoader() {
        //todo: need to implement
    }

    override fun stopLoader(error: Boolean) {
        //todo: need to implement
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


}