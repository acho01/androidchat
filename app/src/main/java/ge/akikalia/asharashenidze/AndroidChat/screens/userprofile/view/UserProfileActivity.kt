package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.model.User
import ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter.RegisterPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.presenter.UserProfilePresenter


class UserProfileActivity : AppCompatActivity(),IUserProfileView {

    private var presenter = UserProfilePresenter(this)

    private lateinit var usernameItem: TextInputLayout
    private lateinit var occupationItem: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        initListeners()
        presenter.loadLoggedUserProfile()
    }

    override fun displayUserProfile(username: String?, occupation: String?) {
        usernameItem = findViewById<TextInputLayout>(R.id.profile_username_txt_field)
        occupationItem = findViewById<TextInputLayout>(R.id.profile_occupation_txt_field)

        usernameItem.getEditText()?.setText(username)
        occupationItem.getEditText()?.setText(occupation)
     }

    override fun getContext(): Context {
        return this
    }

    private fun initListeners() {
        val updateBtn = findViewById<Button>(R.id.profile_update_btn)
        val usernameItem2 = findViewById<TextInputLayout>(R.id.profile_username_txt_field)
        val occupationItem2 = findViewById<TextInputLayout>(R.id.profile_occupation_txt_field)
        updateBtn.setOnClickListener {
            val username = usernameItem2.editText?.text.toString()
            val occupation = occupationItem2.editText?.text.toString()
            presenter.updateLoggedUser(User(username, null, occupation))
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
