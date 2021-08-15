package ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.MainActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.presenter.UserProfilePresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.LoginActivity

class ProfileFragment : Fragment(), IUserProfileView {

    private var presenter = UserProfilePresenter(this)

    private lateinit var usernameItem: TextInputLayout
    private lateinit var occupationItem: TextInputLayout

    private lateinit var profileView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileView = view
        initListeners()
        presenter.loadLoggedUserProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun displayUserProfile(username: String, occupation: String) {
        usernameItem = profileView.findViewById<TextInputLayout>(R.id.profile_username_txt_field)
        occupationItem = profileView.findViewById<TextInputLayout>(R.id.profile_occupation_txt_field)

        usernameItem.getEditText()?.setText(username)
        occupationItem.getEditText()?.setText(occupation)
    }

    override fun getContext(): Context {
        return profileView.context
    }

    private fun initListeners() {
        val updateBtn = profileView.findViewById<Button>(R.id.profile_update_btn)
        val usernameItem2 = profileView.findViewById<TextInputLayout>(R.id.profile_username_txt_field)
        val occupationItem2 = profileView.findViewById<TextInputLayout>(R.id.profile_occupation_txt_field)
        updateBtn.setOnClickListener {
            val username = usernameItem2.editText?.text.toString()
            val occupation = occupationItem2.editText?.text.toString()
            presenter.updateLoggedUser(username, occupation)
        }
        val signOutBtn = profileView.findViewById<Button>(R.id.profile_signout_btn)
        signOutBtn.setOnClickListener {
            presenter.signOut()
        }
    }

    override fun startSignInView() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun problemChangingEmail() {
        Toast.makeText(activity,resources.getText(R.string.profile_change_error_text), Toast.LENGTH_SHORT).show()
    }

    override fun emailChangedSuccessfully() {
        Toast.makeText(activity,resources.getText(R.string.profile_change_success_text), Toast.LENGTH_SHORT).show()
    }

    override fun startLoader() {
        //todo: need to implement
    }

    override fun stopLoader(error: Boolean) {
//        loader.stop()
        //todo: need to implement
        if (error) {

        } else {

        }
    }

}