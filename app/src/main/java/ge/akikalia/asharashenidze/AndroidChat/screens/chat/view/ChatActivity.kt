package ge.akikalia.asharashenidze.AndroidChat.screens.chat.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.Message
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.presenter.ChatPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.ChatUserDto
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.MessageDto
import android.content.Intent
import android.widget.ImageButton


class ChatActivity() : AppCompatActivity(), IChatView{

    companion object{
        const val CHAT_ID = "chat_id"
    }
    override var chatId: String? = null
    private lateinit var chatRecyclerView: RecyclerView
    private var chatAdapter = ChatAdapter(this)
    private val presenter = ChatPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initViews()
        initListeners()
        chatId = intent.getStringExtra(CHAT_ID)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewDidLoad()
    }

    private fun initViews() {
        chatRecyclerView = findViewById<View>(R.id.chat_recycler) as RecyclerView
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = chatAdapter
    }

    private fun initListeners() {
        var sendBtn = findViewById<Button>(R.id.chat_send_btn) as Button
        sendBtn.setOnClickListener {
            var sendInput = findViewById<EditText>(R.id.chat_message_input_edit) as EditText
            var sendMessage = sendInput.text.toString()
            sendInput.setText("")
            presenter.sendMessage(sendMessage)
        }
        findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            finish()
        }
    }
    override fun updateList(list: List<MessageDto>) {
        chatAdapter.messageDtoList = list
        chatAdapter.notifyDataSetChanged()
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

}
