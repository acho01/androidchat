package ge.akikalia.asharashenidze.AndroidChat.screens.chat.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.model.Message
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.IChatView
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.MessageDto

class ChatPresenter(var view: IChatView?) {

    fun onViewDidLoad(){
        val userId = ChatStorage.getUserId()
        if (view?.chatId != null && userId != null){
            view?.startLoader()
            ChatStorage.startListeningToChat(view!!.chatId!!){ list ->
                if (list != null){
                    val newList = list.map { message ->
                        with(message){
                            //todo: need to format timestamp
                            MessageDto(text, userId == senderId, timestamp.toString())
                        }
                    }
                    view?.updateList(newList)
                }
                view?.stopLoader(list == null)
            }
        }
    }

    fun onPause(){
        view = null
        ChatStorage.stopListeningToChat()
    }

    fun sendMessage(text: String){
        if (view?.chatId != null) {
            ChatStorage.sendMessage(view!!.chatId!!, text)
        }
    }
}