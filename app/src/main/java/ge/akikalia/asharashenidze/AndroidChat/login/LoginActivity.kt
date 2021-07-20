package ge.akikalia.asharashenidze.AndroidChat.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ge.akikalia.asharashenidze.AndroidChat.R


class LoginActivity : AppCompatActivity(), ILoginView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}
