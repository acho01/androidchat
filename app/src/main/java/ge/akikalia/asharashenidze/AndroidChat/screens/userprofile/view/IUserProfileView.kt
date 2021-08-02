package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view

import android.content.Context
import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView

interface IUserProfileView: ILoaderView {
    fun displayUserProfile(username: String?, occupation: String?)
    fun getContext(): Context
}