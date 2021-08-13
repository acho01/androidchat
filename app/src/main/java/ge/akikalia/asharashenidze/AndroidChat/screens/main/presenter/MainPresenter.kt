package ge.akikalia.asharashenidze.AndroidChat.screens.main.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorkerError
import ge.akikalia.asharashenidze.AndroidChat.model.Conversation
import ge.akikalia.asharashenidze.AndroidChat.model.UserChat
import ge.akikalia.asharashenidze.AndroidChat.screens.main.view.IMainView

class MainPresenter(private val view: IMainView): IMainPresenter {

    init {
        var userId = FirebaseAuthWorker.getUser()?.tenantId
        userId = "acho01"
        FirebaseDbWorker.listenForChatListChanges(userId) { error, list -> listenCallback(error, list) }
    }

    private fun listenCallback(error: FirebaseDbWorkerError, list: List<UserChat>?): Unit{
            if (error == FirebaseDbWorkerError.SUCCESS || list != null){
                val conversationList = list!!.map { userChat ->
                    with(userChat){
                        Conversation(partnerUsername, lastMessage, lastMessageTime, chatId, null)
                    }
                }
                view.updateConversations(conversationList)
            }else{
                view.displayError()
            }
    }
}