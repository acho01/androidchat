package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview

interface IMainChatListView: ILoaderView {
    fun updateList(list: List<ChatPreview>)
    fun listItemClickedWithId(id: String)
}