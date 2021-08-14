package ge.akikalia.asharashenidze.AndroidChat.screens.chat.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.ChatUserDto
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.MessageDto


class ChatActivity : AppCompatActivity(){

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageList: List<MessageDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageList = listOf(
            MessageDto("Hello My Friend", ChatUserDto("acho01"), "13:34"),
            MessageDto("Hi Wazappp", ChatUserDto("ako02"), "13:34"),
            MessageDto("Let's do it", ChatUserDto("acho01"), "13:34"),
            MessageDto("OOOh yeah let's fuckin do it", ChatUserDto("ako02"), "13:34"),
            MessageDto("Let's meet at uni", ChatUserDto("acho01"), "13:34"),
            MessageDto("I'll be in 40 mins", ChatUserDto("ako02"), "13:34"),
            MessageDto("I ve just arrived", ChatUserDto("acho01"), "13:34"),
            MessageDto("Great, Ill be soon to", ChatUserDto("ako02"), "13:34"),
            MessageDto("Grab a cup of coffee pls", ChatUserDto("acho01"), "13:34"),
            MessageDto("kk", ChatUserDto("ako02"), "13:34")
        )

        initViews()
        initListeners()
    }

    private fun initViews() {
        chatRecyclerView = findViewById<View>(R.id.chat_recycler) as RecyclerView
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = ChatAdapter(this, messageList)
    }

    private fun initListeners() {
        var sendBtn = findViewById<Button>(R.id.chat_send_btn) as Button
        sendBtn.setOnClickListener {
            var sendInput = findViewById<EditText>(R.id.chat_message_input_edit) as EditText
            var sendMessage = sendInput.text.toString()
            sendInput.setText("")
        }

    }
}
