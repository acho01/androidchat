package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import ge.akikalia.asharashenidze.AndroidChat.model.UserData

interface IAddView {
    fun updateList(list: List<UserData>)
    fun displayError()
    fun addUser(id: String)
}