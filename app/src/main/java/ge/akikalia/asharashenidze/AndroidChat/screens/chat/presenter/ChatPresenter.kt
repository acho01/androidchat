package ge.akikalia.asharashenidze.AndroidChat.screens.chat.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.IChatView
import ge.akikalia.asharashenidze.AndroidChat.screens.login.view.ILoginView

class ChatPresenter(view: IChatView): FirebaseAuthWorkerDelegate {

    var view: IChatView? = view

    fun onCreate(){
        FirebaseAuthWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseAuthWorker.delegate = null
        view = null
    }

}