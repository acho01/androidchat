package ge.akikalia.asharashenidze.AndroidChat.register.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerError

class RegisterPresenter: FirebaseWorkerDelegate {

    fun onCreate(){
        FirebaseWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseWorker.delegate = null
    }


    fun signUpClicked(username: String, password: String, occupation: String){
        FirebaseWorker.signUpUser(username, password){
            FirebaseWorker.addOccupation(occupation)
        }
    }

    override fun firebaseWorkerDidSignUpUser(sender: FirebaseWorker, error: FirebaseWorkerError) {
        TODO("Not yet implemented")
    }

    override fun firebaseWorkerDidSignInUser(sender: FirebaseWorker, error: FirebaseWorkerError) {
        TODO("Not yet implemented")
    }

    override fun firebaseWorkerDidAddOccupation(
        sender: FirebaseWorker,
        error: FirebaseWorkerError
    ) {
        TODO("Not yet implemented")
    }
}