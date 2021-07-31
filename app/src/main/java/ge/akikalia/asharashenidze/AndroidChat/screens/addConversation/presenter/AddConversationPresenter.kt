package ge.akikalia.asharashenidze.AndroidChat.screens.addConversation.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.screens.addConversation.view.IAddConversationView
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.IChatView

class AddConversationPresenter(view: IAddConversationView): FirebaseAuthWorkerDelegate {

    var view: IAddConversationView? = view

    fun onCreate(){
        FirebaseAuthWorker.delegate = this
    }

    fun onDestroy(){
        FirebaseAuthWorker.delegate = null
        view = null
    }

}