package ge.akikalia.asharashenidze.AndroidChat.screens.addConversation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.screens.addConversation.presenter.AddConversationPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.presenter.ChatPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.IChatView

class AddConversationActivity : AppCompatActivity(), IAddConversationView {

    private var presenter = AddConversationPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_conversation)
        presenter.onCreate()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initViews() {
        FirebaseDbWorker.getData()
        FirebaseDbWorker.setData()
    }

    override fun startLoader() {
        // maybe create an extension for Activity for loader methods
    }

    override fun stopLoader(error: Boolean) {
//        loader.stop()
        if (error) {

        } else {

        }
    }
}
