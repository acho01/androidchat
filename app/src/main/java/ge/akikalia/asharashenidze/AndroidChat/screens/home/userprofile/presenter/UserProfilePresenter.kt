package ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.common.Action
import ge.akikalia.asharashenidze.AndroidChat.common.ToastUtils
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorkerError
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import ge.akikalia.asharashenidze.AndroidChat.screens.home.userprofile.view.IUserProfileView

class UserProfilePresenter(var view: IUserProfileView?) : FirebaseDbWorkerDelegate {

    fun onCreate() {
//        FirebaseDbWorker.delegate = this
    }

    fun onDestroy() {
        FirebaseAuthWorker.delegate = null
        view = null
    }

    fun loadLoggedUserProfile() {
        view?.startLoader()
        Log.i("stdout", "loading logged user...")
//        FirebaseDbWorker.loadLoggedUser { status, user ->
//            updateDisplay(Action.LOAD, status, user)
//        }
    }

    fun updateLoggedUser(updatedUser: UserData) {
        view?.startLoader()
//        Log.i("stdout", "Updating logged user...")
//        FirebaseDbWorker.updateLoggedUser(updatedUser) { status, user ->
//           updateDisplay(Action.UPDATE, status, user)
//        }
    }

    fun updateDisplay(action: Action, status: FirebaseDbWorkerError, user: UserData?) {
        if (status == FirebaseDbWorkerError.SUCCESS){
            Log.i("stdout", "Logged User $action successfully")
            if (user != null) {
                view?.displayUserProfile(user.username, user.occupation)
                if (action == Action.UPDATE) {
                    ToastUtils.toast("User Updated!", view?.getContext())
                } else if (action == Action.LOAD) {
                    ToastUtils.toast("User Loaded!", view?.getContext())
                }
            } else {
                Log.i("stdout", "Logged User $action failed!")
            }
        }else{
            Log.i("stdout", "Logged User $action failed!")
        }
    }

}