package ge.akikalia.asharashenidze.AndroidChat.screens.register.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.register.view.IRegisterView

class RegisterPresenter(view: IRegisterView) {

    var view: IRegisterView? = view

    fun onDestroy(){
        view = null
    }

    fun signUpClicked(username: String, password: String, occupation: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        ChatStorage.signUp(username, password, occupation, "", null){ success ->
            view?.stopLoader(false)
            if (success)
                view?.startMainView()
            else
                view?.showRegisterError()
        }
    }
}