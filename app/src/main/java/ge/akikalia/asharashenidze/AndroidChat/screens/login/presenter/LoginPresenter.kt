package ge.akikalia.asharashenidze.AndroidChat.screens.login.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.ILoginView

class LoginPresenter(view: ILoginView): FirebaseAuthWorkerDelegate {

    var view: ILoginView? = view

    fun onCreate(){
        FirebaseAuthWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseAuthWorker.delegate = null
        view = null
    }


    fun signInClicked(username: String, password: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        FirebaseAuthWorker.signInUser(username, password){ result ->

        }
    }
}