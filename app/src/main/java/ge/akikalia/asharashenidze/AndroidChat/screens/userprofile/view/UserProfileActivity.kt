package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ge.akikalia.asharashenidze.AndroidChat.R


class UserProfileActivity : AppCompatActivity(),IUserProfileView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


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
