package ge.akikalia.asharashenidze.AndroidChat.screens.home.main.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview
import ge.akikalia.asharashenidze.AndroidChat.screens.home.main.dto.ChatPreviewDto

interface IMainChatListView: ILoaderView {
    fun updateList(list: List<ChatPreviewDto>)
    fun listItemClickedWithId(chatId: String, sender: String)
}