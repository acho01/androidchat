package ge.akikalia.asharashenidze.AndroidChat.screens.add.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorkerError
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.model.UserChat
import ge.akikalia.asharashenidze.AndroidChat.screens.add.view.IAddView
import ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter.IMainPresenter
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.IMainView

class AddPresenter(private val view: IAddView): IAddPresenter {
    fun search(text: String){
        FirebaseDbWorker.getUserList(text){ error, list ->
            if (error == FirebaseDbWorkerError.SUCCESS && list != null){
                view.updateList(list!!)
            }else{
                view.displayError()
            }
        }
    }
}