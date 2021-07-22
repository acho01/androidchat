package ge.akikalia.asharashenidze.AndroidChat.data.firebase.db

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError

object FirebaseDbWorker {
    val database = Firebase.database
    val myRef = database.getReference("message")

    var delegate: FirebaseDbWorkerDelegate? = null

    fun addOccupation(occupation: String, onSuccess: (FirebaseDbWorkerError) -> Unit) {
        myRef.setValue("Hello, World!")

    }

}