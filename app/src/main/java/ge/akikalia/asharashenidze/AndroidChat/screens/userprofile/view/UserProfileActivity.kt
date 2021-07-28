package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter.RegisterPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.presenter.UserProfilePresenter


class UserProfileActivity : AppCompatActivity(),IUserProfileView {

    private var presenter = UserProfilePresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        presenter.loadLoggedUserProfile()

    }

    override fun displayUserProfile(username: String, occupation: String) {
        val usernameItem = findViewById<TextInputLayout>(R.id.profile_username_txt_field)
        val occupationItem = findViewById<TextInputLayout>(R.id.profile_occupation_txt_field)

        usernameItem.getEditText()?.setText(username)
        occupationItem.getEditText()?.setText(occupation)
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
