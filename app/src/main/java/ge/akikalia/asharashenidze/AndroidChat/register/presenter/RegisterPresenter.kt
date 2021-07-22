package ge.akikalia.asharashenidze.AndroidChat.register.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.register.view.IRegisterView

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
            if (result == FirebaseAuthWorkerError.SUCCESS){
                Log.i("stdout", "sign up completed Successfully")
                FirebaseAuthWorker.addOccupation(occupation){ result ->
                    view?.stopLoader(result == FirebaseAuthWorkerError.SUCCESS)
                }
            }else{
                Log.i("stdout", "sign up complete Unsuccessfully")
            }
        }
    }
}