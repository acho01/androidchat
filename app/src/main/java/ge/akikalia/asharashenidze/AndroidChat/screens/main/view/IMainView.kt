package ge.akikalia.asharashenidze.AndroidChat.screens.main.view

import ge.akikalia.asharashenidze.AndroidChat.model.Conversation

interface IMainView {
    fun updateConversations(list: List<Conversation>)
    fun displayError()
}