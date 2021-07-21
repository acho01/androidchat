package ge.akikalia.asharashenidze.AndroidChat.register.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ge.akikalia.asharashenidze.AndroidChat.R


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameItem = findViewById<TextInputLayout>(R.id.register_username_txt_field)
        val passwordItem = findViewById<TextInputLayout>(R.id.register_password_txt_field)
        val descItem = findViewById<TextInputLayout>(R.id.register_desc_txt_field)


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

                TextUtils.isEmpty(descItem.editText?.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Please enter what you do",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val username = usernameItem.editText?.text.toString().trim { it <= ' ' }
                    val password = passwordItem.editText?.text.toString().trim { it <= ' ' }
                    val description = descItem.editText?.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
                        .addOnCompleteListener(
                            {task ->
                                if (task.isSuccessful) {
                                    val firebaseuser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this,
                                        "You are registered successfuly",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    println(task.exception.toString())
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        )
                }
            }

        }
    }
}
