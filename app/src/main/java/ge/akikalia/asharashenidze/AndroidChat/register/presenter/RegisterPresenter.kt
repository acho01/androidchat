package ge.akikalia.asharashenidze.AndroidChat.register.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.FirebaseWorkerError
import ge.akikalia.asharashenidze.AndroidChat.main.view.IMainView
import ge.akikalia.asharashenidze.AndroidChat.register.view.IRegisterView

class RegisterPresenter(view: IRegisterView): FirebaseWorkerDelegate {

    var view: IRegisterView? = view

    fun onCreate(){
        FirebaseWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseWorker.delegate = null
        view = null
    }


    fun signUpClicked(username: String, password: String, occupation: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        FirebaseWorker.signUpUser(username, password){ result ->
            if (result == FirebaseWorkerError.SUCCESS){
                Log.i("stdout", "sign up completed Successfully")
                FirebaseWorker.addOccupation(occupation){ result ->
                    view?.stopLoader(result == FirebaseWorkerError.SUCCESS)
                }
            }else{
                Log.i("stdout", "sign up complete Unsuccessfully")
            }
        }
    }
}