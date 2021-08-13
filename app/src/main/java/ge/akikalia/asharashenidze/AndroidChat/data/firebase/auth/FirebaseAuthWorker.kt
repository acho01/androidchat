package ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


object FirebaseAuthWorker {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var delegate: FirebaseAuthWorkerDelegate? = null

    fun getUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signUpUser(username: String, password: String, onSuccess: (FirebaseAuthWorkerError)->Unit) {
        mAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseuser: FirebaseUser = task.result!!.user!!
                    onSuccess(FirebaseAuthWorkerError.SUCCESS)
                } else {
                    onSuccess(FirebaseAuthWorkerError.FAILURE)
                }
            }
    }

    fun signInUser(username: String, password: String, onSuccess: (FirebaseAuthWorkerError) -> Unit) {
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseuser: FirebaseUser = task.result!!.user!!
                    onSuccess(FirebaseAuthWorkerError.SUCCESS)
                } else {
                    onSuccess(FirebaseAuthWorkerError.FAILURE)
                }
            }
    }

    fun addOccupation(occupation: String, onSuccess: (FirebaseAuthWorkerError) -> Unit) {

    }
}