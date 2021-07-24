package ge.akikalia.asharashenidze.AndroidChat.data.firebase.db

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError

object FirebaseDbWorker {
    val database = Firebase.database

    val myRef = database.getReference("message")

    var delegate: FirebaseDbWorkerDelegate? = null

    init {
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d("stdout", "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("stdout", "Failed to read value.", error.toException())
            }

        })
    }

    fun addOccupation(occupation: String, onSuccess: (FirebaseDbWorkerError) -> Unit) {
        myRef.setValue("Hello, World!")

    }

}