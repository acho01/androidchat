package ge.akikalia.asharashenidze.AndroidChat.screens.main.view

import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview

interface IMainView {
    fun updateConversations(list: List<ChatPreview>)
    fun displayError()
}