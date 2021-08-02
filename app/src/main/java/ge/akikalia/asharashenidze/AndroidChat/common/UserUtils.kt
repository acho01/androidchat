package ge.akikalia.asharashenidze.AndroidChat.common

import com.google.firebase.database.DataSnapshot
import ge.akikalia.asharashenidze.AndroidChat.model.User

object UserUtils {

    const val USERNAME_KEY = "username"

    const val PASSWORD_KEY = "password"

    const val OCCUPATION_KEY = "occupation"


    @JvmStatic
    fun getUser(snapshot: DataSnapshot): User {
        val hashMap = snapshot.value as HashMap<*, *>
        val username = hashMap[USERNAME_KEY].toString()
        val password = hashMap[PASSWORD_KEY].toString()
        val occupation = hashMap[OCCUPATION_KEY].toString()

        return User(username, password, occupation)
    }
}