package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.presenter

import android.util.Log
import android.util.TimeUtils
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.dto.ChatPreviewDto
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view.IMainChatListView
import java.sql.Time

class MainChatListPresenter(var view: IMainChatListView?) {

    fun onViewDidLoad(){
        view?.startLoader()
        ChatStorage.startListeningToChatPreviewList { list->
            Log.i("stdout", "list = ${list?.map { it.lastMessage } }}")
            if (list != null){
                val dtoList = list.map { chatPreview ->
                    ChatPreviewDto(
                        chatPreview.id,
                        chatPreview.username,
                        chatPreview.respondentId,
                        chatPreview.lastMessage,
                        ge.akikalia.asharashenidze.AndroidChat.common.TimeUtils.format(chatPreview.timestamp)
                    )
                }
                view?.updateList(dtoList)
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