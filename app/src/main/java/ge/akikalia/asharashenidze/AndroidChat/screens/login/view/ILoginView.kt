package ge.akikalia.asharashenidze.AndroidChat.screens.login.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView

interface ILoginView: ILoaderView {
    fun startMainView()
    fun showLoginError()
}