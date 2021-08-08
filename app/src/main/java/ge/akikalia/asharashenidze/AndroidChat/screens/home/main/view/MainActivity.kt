package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter.MainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.ProfileFragment


class MainActivity : AppCompatActivity() {

    val presenter = MainPresenter()
    private var mainListFragment = MainChatListFragment()
    private var profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavBarListeners()
        replaceFragment(mainListFragment)
    }

    private fun initNavBarListeners() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_btn -> replaceFragment(mainListFragment)
                R.id.profile_btn -> replaceFragment(profileFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}