package ge.akikalia.asharashenidze.AndroidChat.screens.login.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.screens.login.presenter.LoginPresenter


class LoginActivity : AppCompatActivity(), ILoginView {
    lateinit var usernameItem: TextInputLayout
    lateinit var passwordItem: TextInputLayout

    private var presenter = LoginPresenter(this)

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

    private fun initViews() {
        FirebaseDbWorker.getData()
        FirebaseDbWorker.setData()


        usernameItem = findViewById<TextInputLayout>(R.id.login_username_txt_field)
        passwordItem = findViewById<TextInputLayout>(R.id.login_password_txt_field)
        findViewById<Button>(R.id.login_sign_in_btn).setOnClickListener {
            when {
                TextUtils.isEmpty(usernameItem.editText?.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter username",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(passwordItem.editText?.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter password",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    val username = usernameItem.editText?.text.toString().trim { it <= ' ' }
                    val password = passwordItem.editText?.text.toString().trim { it <= ' ' }

                    presenter.signInClicked(
                        username as String,
                        password as String,
                    )

                }
            }
        }
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
