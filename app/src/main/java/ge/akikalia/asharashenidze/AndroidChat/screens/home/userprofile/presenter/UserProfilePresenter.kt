package ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.common.Action
import ge.akikalia.asharashenidze.AndroidChat.common.ToastUtils
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorkerError
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.IUserProfileView

class UserProfilePresenter(var view: IUserProfileView?) {

    fun onCreate() {
//        FirebaseDbWorker.delegate = this
    }

    fun onDestroy() {
        view = null
    }

    fun loadLoggedUserProfile() {
        view?.startLoader()
        Log.i("stdout", "loading logged user...")
        ChatStorage.getUserData { user ->
            if (user != null){
                view?.displayUserProfile(user.username, user.occupation)
            }
            view?.stopLoader(user == null)
        }
    //        FirebaseDbWorker.loadLoggedUser { status, user ->
//            updateDisplay(Action.LOAD, status, user)
//        }
    }

    fun updateLoggedUser(username: String, occupation: String) {
        view?.startLoader()
        if (ChatStorage.changeProfile(username, occupation))
            loadLoggedUserProfile()
        else
            view?.problemChangingEmail()
    }

    fun signOut(){
        ChatStorage.signOut()
        view?.startSignInView()
    }

}