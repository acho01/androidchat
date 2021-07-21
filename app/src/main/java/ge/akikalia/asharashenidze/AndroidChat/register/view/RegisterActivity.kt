package ge.akikalia.asharashenidze.AndroidChat.register.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.register.presenter.RegisterPresenter
import kotlin.math.sign


class RegisterActivity : AppCompatActivity(){
    lateinit var username: TextView
    lateinit var password: TextView
    lateinit var occupation: TextView

    private var presenter = RegisterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onCreate()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initViews(){
        username = findViewById<Button>(R.id.registration_nickname)
        password = findViewById<Button>(R.id.registration_password)
        occupation = findViewById<Button>(R.id.registration_occupation)
        findViewById<Button>(R.id.sign_up_btn).setOnClickListener {
            presenter.signUpClicked(username.text as String, password.text as String, occupation.text as String)
        }
    }

    // maybe create an extension for Activity for loader methods
    private fun startLoader(){
//        loader.start()
    }

    private fun stopLoader(error: Boolean){
//        loader.stop()
        if (error){

        }else{

        }
    }

}
