package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity.Companion.CHAT_ID
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity.Companion.SENDER_ID
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.dto.ChatPreviewDto
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter.MainChatListPresenter

class MainChatListFragment : Fragment(), IMainChatListView {
    private lateinit var chatPreviewListRecyclerView: RecyclerView
    private val chatPreviewListAdapter = ChatPreviewListAdapter(this)

    val presenter = MainChatListPresenter(this)
    private lateinit var mainView: View
    lateinit var chatPreviewList: List<ChatPreviewDto>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainView = view
        initViews()
        initListeners()
    }

    private fun initListeners() {
        mainView.findViewById<TextInputEditText>(R.id.chat_list_search_input).addTextChangedListener {
            updateAdapterList(chatPreviewList)
        }
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

    override fun updateList(list: List<ChatPreviewDto>){
        chatPreviewList = list;
        updateAdapterList(chatPreviewList)
    }

    private fun updateAdapterList(list: List<ChatPreviewDto>) {
        chatPreviewListAdapter.list = list.
        filter { chatPreview -> chatPreview.lastMessage.equals("").not() }.
        filter{chatPreview -> chatPreview.username.contains(mainView.findViewById<TextInputEditText>(R.id.chat_list_search_input).text.toString()) }
        chatPreviewListAdapter.notifyDataSetChanged()
    }

    override fun listItemClickedWithId(chatId: String, sender: String) {
        val intent = Intent(activity, ChatActivity::class.java).apply {
            putExtra(CHAT_ID, chatId)
            putExtra(SENDER_ID, sender)
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