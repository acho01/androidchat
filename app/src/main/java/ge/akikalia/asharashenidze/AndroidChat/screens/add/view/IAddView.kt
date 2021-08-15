package ge.akikalia.asharashenidze.AndroidChat.screens.add.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView
import ge.akikalia.asharashenidze.AndroidChat.model.UserData

interface IAddView: ILoaderView {
    fun updateList(list: List<UserData>)
    fun closeView()
    fun addUser(id: String)
}