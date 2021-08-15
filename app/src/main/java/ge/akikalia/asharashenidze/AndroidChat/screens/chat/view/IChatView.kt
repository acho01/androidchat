package ge.akikalia.asharashenidze.AndroidChat.screens.chat.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.model.Message
import ge.akikalia.asharashenidze.AndroidChat.screens.chat.view.dto.MessageDto

interface IChatView: ILoaderView {
    abstract val chatId: String?
    fun updateList(list: List<MessageDto>)
}