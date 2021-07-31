package ge.akikalia.asharashenidze.AndroidChat.screens.chat.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.presenter.ChatPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.login.presenter.LoginPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.ILoginView

class ChatActivity : AppCompatActivity(), IChatView {

    private var presenter = ChatPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
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
