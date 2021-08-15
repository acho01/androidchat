package ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view

import android.content.Context
import ge.akikalia.asharashenidze.AndroidChat.common.ILoaderView

interface IUserProfileView: ILoaderView {
    fun displayUserProfile(username: String, occupation: String)
    fun startSignInView()
    fun problemChangingEmail()
}