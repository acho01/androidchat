package ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.register.view.IRegisterView

class RegisterPresenter(view: IRegisterView): FirebaseAuthWorkerDelegate {

    var view: IRegisterView? = view

    fun onCreate(){
        FirebaseAuthWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseAuthWorker.delegate = null
        view = null
    }


    fun signUpClicked(username: String, password: String, occupation: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        FirebaseAuthWorker.signUpUser(username, password){ result ->

        }
    }
}