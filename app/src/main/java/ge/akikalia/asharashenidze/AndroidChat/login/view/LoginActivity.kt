package ge.akikalia.asharashenidze.AndroidChat.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.login.view.ILoginView


class LoginActivity : AppCompatActivity(), ILoginView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}
