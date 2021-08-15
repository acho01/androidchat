package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.IMainChatListView

class MainChatListPresenter(var view: IMainChatListView?) {

    fun onViewDidLoad(){
        view?.startLoader()
        ChatStorage.startListeningToChatPreviewList { list->
            Log.i("stdout", "list = ${list?.map { it.lastMessage } }}")
            if (list != null){
                view?.updateList(list)
            }
            view?.stopLoader(list == null)
        }
    }

    fun onPause(){
        ChatStorage.stopListeningToChatPreviewList()
    }

    fun onDestroy(){
        view = null
    }
}