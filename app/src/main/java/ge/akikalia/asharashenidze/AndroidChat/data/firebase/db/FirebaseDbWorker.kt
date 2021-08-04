package ge.akikalia.asharashenidze.AndroidChat.data.firebase.db

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ge.akikalia.asharashenidze.AndroidChat.common.UserChatUtils
import ge.akikalia.asharashenidze.AndroidChat.common.UserUtils
import ge.akikalia.asharashenidze.AndroidChat.model.User
import ge.akikalia.asharashenidze.AndroidChat.model.UserChat

object FirebaseDbWorker {
    val database = FirebaseDatabase.getInstance()

    val myRef = database.getReference("Users").child("acho")

    val myRefUsers = database.getReference("Users")

    val myRefChats = database.getReference("Chats")

    val myRefUserChats = database.getReference("UserChats")

    var delegate: FirebaseDbWorkerDelegate? = null

    init {

    }

    fun setListener(){
        Log.i("stdout", "initialized")
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue(String::class.java)
                Log.d("stdout", "Value is: " + value)
//                setValue()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("stdout", "Failed to read value.", error.toException())
            }

        })
    }

    fun setValue(){
        myRef.setValue("prarabi")
    }

    fun getValue(){
        myRef.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("stdout", "single value event listener{")
                val value = snapshot.getValue(String::class.java)
                Log.d("stdout", "Value is: " + value)
                Log.d("stdout", "}")
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("stdout", "cancelled")
            }
        })
    }

    fun loadLoggedUser(onSuccess: (FirebaseDbWorkerError, User?) -> Unit) {
        myRefUsers.child("acho01").get().addOnSuccessListener {
            onSuccess(FirebaseDbWorkerError.SUCCESS, UserUtils.getUser(it))
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            onSuccess(FirebaseDbWorkerError.FAILURE, null)
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun updateLoggedUser(updatedUser: User, onSuccess: (FirebaseDbWorkerError, User?) -> Unit) {
            myRefUsers.get().addOnSuccessListener {
                val loadedUser = UserUtils.getUser(it)
                val updateDbUser = UserUtils.updateDbUser(myRefUsers, loadedUser, updatedUser)
                onSuccess(FirebaseDbWorkerError.SUCCESS, updateDbUser)
                Log.i("firebase", "User updated successfully ${updateDbUser}")
            }.addOnFailureListener {
                onSuccess(FirebaseDbWorkerError.FAILURE, null)
                Log.i("firebase", "User update Failed ${updatedUser.username}")
            }
    }

    fun getUserChatPreviews(username: String?, onSuccess: (FirebaseDbWorkerError, List<UserChat>?) -> Unit) {
        if (username != null) {
            myRefUserChats.child(username).get().addOnSuccessListener {
                val userChatPreviews = UserChatUtils.getChatPreviews(it)
                onSuccess(FirebaseDbWorkerError.SUCCESS, userChatPreviews)
                Log.i("firebase", "Loaded ${userChatPreviews?.size} user chat previews")
            }.addOnFailureListener {
                onSuccess(FirebaseDbWorkerError.FAILURE, null)
                Log.i("firebase", "User Chat Preview Load failed!")
            }
        } else {
            onSuccess(FirebaseDbWorkerError.FAILURE, null)
            Log.i("firebase", "User Chat Preview Load failed!")
        }
    }

    fun addOccupation(occupation: String, onSuccess: (FirebaseDbWorkerError) -> Unit) {
        myRef.setValue("Hello, World!")
    }
}