package ge.akikalia.asharashenidze.AndroidChat.common

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import ge.akikalia.asharashenidze.AndroidChat.model.User
import ge.akikalia.asharashenidze.AndroidChat.model.UserChat

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

    @JvmStatic
    fun updateDbUser(userRef: DatabaseReference, loadedUser: User, updatedUser: User): User {
        val oldUsername = loadedUser.username
        val oldOccupation = loadedUser.occupation

        val newUsername = updatedUser.username
        val newOccupation = updatedUser.occupation

        if (oldUsername.equals(newUsername).not()) {
            userRef.child(USERNAME_KEY).setValue(newUsername)
        }

        if (oldOccupation.equals(newOccupation).not()) {
            userRef.child(OCCUPATION_KEY).setValue(newOccupation)
        }

        return User(
            newUsername,
            newOccupation
        )
    }
}