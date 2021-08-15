package ge.akikalia.asharashenidze.AndroidChat.screens.register.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.common.ToastUtils
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.MainActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter.RegisterPresenter


class RegisterActivity : AppCompatActivity(),IRegisterView {
    lateinit var usernameItem: TextInputLayout
    lateinit var passwordItem: TextInputLayout
    lateinit var occupationItem: TextInputLayout

    var imageView: ImageView? = null
    var imageByteArray: ByteArray? = null

    private var presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initViews()
        initImagePicker()
    }

    private fun initImagePicker() {
        imageView = findViewById(R.id.register_avatar_view)

        imageView?.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode== ImagePicker.REQUEST_CODE) {
            val uri = data?.data
            if (uri != null) {
                imageByteArray = contentResolver.openInputStream(uri)?.readBytes()
            }
            imageView?.setImageURI(uri)
        } else {
            ToastUtils.toast("Error while uploading image", this)
        }
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
                        occupation as String,
                        imageByteArray
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
