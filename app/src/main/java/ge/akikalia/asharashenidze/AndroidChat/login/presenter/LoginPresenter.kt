package ge.akikalia.asharashenidze.AndroidChat.login.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerError
import ge.akikalia.asharashenidze.AndroidChat.login.view.ILoginView

class LoginPresenter(view: ILoginView): FirebaseWorkerDelegate {

    var view: ILoginView? = view

    fun onCreate(){
        FirebaseWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseWorker.delegate = null
        view = null
    }


    fun signInClicked(username: String, password: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        FirebaseWorker.signInUser(username, password){ result ->
            if (result == FirebaseWorkerError.SUCCESS){
                Log.i("stdout", "sign in completed Successfully")
            }else{
                Log.i("stdout", "sign in complete Unsuccessfully")
            }
        }
    }
}