package ge.akikalia.asharashenidze.AndroidChat.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener


object FirebaseWorker {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var delegate: FirebaseWorkerDelegate? = null


    fun getUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signUpUser(username: String, password: String, onSuccess: (FirebaseWorkerError)->Unit) {
        mAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseuser: FirebaseUser = task.result!!.user!!
                    onSuccess(FirebaseWorkerError.SUCCESS)
                } else {
                    onSuccess(FirebaseWorkerError.FAILURE)
                }
            }
    }

    fun signInUser(username: String, password: String, onSuccess: (FirebaseWorkerError) -> Unit) {
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseuser: FirebaseUser = task.result!!.user!!
                    onSuccess(FirebaseWorkerError.SUCCESS)
                } else {
                    onSuccess(FirebaseWorkerError.FAILURE)
                }
            }
    }

    fun addOccupation(occupation: String, onSuccess: (FirebaseWorkerError) -> Unit) {

    }
}