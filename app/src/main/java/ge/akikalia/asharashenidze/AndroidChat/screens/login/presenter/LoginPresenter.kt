package ge.akikalia.asharashenidze.AndroidChat.screens.login.presenter

import android.content.Intent
import android.util.Log
import android.widget.Toast
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.MainActivity
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.ILoginView

class LoginPresenter(view: ILoginView) {

    var view: ILoginView? = view

    fun onCreate(){
        if (ChatStorage.isSignedIn()){
            view?.startMainView()
        }
    }

    fun onDestroy(){
        view = null
    }

    fun signInClicked(username: String, password: String){
        view?.startLoader()
        Log.i("stdout", "signing up user")
        ChatStorage.signIn(username, password){ success ->
            view?.stopLoader(false)
            if (success)
                view?.startMainView()
            else
                view?.showLoginError()
        }
    }
}