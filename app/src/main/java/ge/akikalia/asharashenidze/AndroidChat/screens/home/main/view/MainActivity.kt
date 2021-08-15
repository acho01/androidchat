package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ge.akikalia.asharashenidze.AndroidChat.R
import ge.akikalia.asharashenidze.AndroidChat.screens.add.view.AddActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.ChatActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.ProfileFragment


class MainActivity : AppCompatActivity() {

    private var mainListFragment = MainChatListFragment()
    private var profileFragment = ProfileFragment()
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavBarListeners()
        replaceFragment(mainListFragment)
        initViews()

    }

    private fun initViews(){
        addButton = findViewById(R.id.fab_button)
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
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