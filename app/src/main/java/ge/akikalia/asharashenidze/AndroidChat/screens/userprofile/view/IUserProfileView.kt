package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view

import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView

interface IUserProfileView: ILoaderView {
    fun displayUserProfile(username: String, occupation: String)
}