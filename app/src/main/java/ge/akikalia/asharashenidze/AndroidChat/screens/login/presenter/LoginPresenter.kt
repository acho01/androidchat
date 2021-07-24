package ge.akikalia.asharashenidze.AndroidChat.screens.login.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError
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
            if (result == FirebaseAuthWorkerError.SUCCESS){
                Log.i("stdout", "sign in completed Successfully")
            }else{
                Log.i("stdout", "sign in complete Unsuccessfully")
            }
        }
    }
}