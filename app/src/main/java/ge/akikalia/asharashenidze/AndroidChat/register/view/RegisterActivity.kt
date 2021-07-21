package ge.akikalia.asharashenidze.AndroidChat.register.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.register.presenter.RegisterPresenter


class RegisterActivity : AppCompatActivity() {
    lateinit var usernameItem: TextInputLayout
    lateinit var passwordItem: TextInputLayout
    lateinit var occupationItem: TextInputLayout

    private var presenter = RegisterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter.onCreate()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initViews() {
        usernameItem = findViewById<TextInputLayout>(R.id.register_username_txt_field)
        passwordItem = findViewById<TextInputLayout>(R.id.register_password_txt_field)
        occupationItem = findViewById<TextInputLayout>(R.id.register_desc_txt_field)
        findViewById<Button>(R.id.sign_up_btn).setOnClickListener {
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

                TextUtils.isEmpty(occupationItem.editText?.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter what you do",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val username = usernameItem.editText?.text.toString().trim { it <= ' ' }
                    val password = passwordItem.editText?.text.toString().trim { it <= ' ' }
                    val occupation = occupationItem.editText?.text.toString().trim { it <= ' ' }

                    presenter.signUpClicked(
                        username as String,
                        password as String,
                        occupation as String
                    )

                }
            }
        }

        // maybe create an extension for Activity for loader methods
        fun startLoader() {
//        loader.start()
        }

        fun stopLoader(error: Boolean) {
//        loader.stop()
            if (error) {

            } else {

            }
        }

    }
}
