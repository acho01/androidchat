package ge.akikalia.asharashenidze.AndroidChat.screens.register.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.MainActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter.RegisterPresenter


class RegisterActivity : AppCompatActivity(),IRegisterView {
    lateinit var usernameItem: TextInputLayout
    lateinit var passwordItem: TextInputLayout
    lateinit var occupationItem: TextInputLayout

    private var presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
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
    }

    override fun startMainView() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun showRegisterError() {
        Toast.makeText(getApplicationContext(),resources.getText(R.string.register_error_text),Toast.LENGTH_SHORT).show()
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
