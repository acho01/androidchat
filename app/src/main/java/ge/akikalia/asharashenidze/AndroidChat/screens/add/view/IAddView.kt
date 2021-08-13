package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import ge.akikalia.asharashenidze.AndroidChat.model.User

interface IAddView {
    fun updateList(list: List<User>)
    fun displayError()
}