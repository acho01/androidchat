package ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


object FirebaseAuthWorker {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var delegate: FirebaseAuthWorkerDelegate? = null

    fun getUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signUpUser(email: String, password: String, onComplete: (FirebaseUser?)->Unit) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    onComplete(firebaseUser)
                } else {
                    onComplete(null)
                }
            }
    }

    fun signInUser(email: String, password: String, onComplete: (FirebaseUser?)->Unit) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    onComplete(firebaseUser)
                } else {
                    onComplete(null)
                }
            }
    }

    fun signOut() {
        mAuth.signOut()
    }
}