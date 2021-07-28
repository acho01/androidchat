package ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.presenter

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.auth.FirebaseAuthWorkerError
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorkerDelegate
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.db.FirebaseDbWorkerError
import ge.akikalia.asharashenidze.AndroidChat.screens.userprofile.view.IUserProfileView

class UserProfilePresenter(var view: IUserProfileView?) : FirebaseDbWorkerDelegate {

    fun onCreate() {
        FirebaseDbWorker.delegate = this
    }

    fun onDestroy() {
        FirebaseAuthWorker.delegate = null
        view = null
    }

    fun loadLoggedUserProfile() {
        view?.startLoader()
        Log.i("stdout", "loading logged user.")
        FirebaseDbWorker.loadLoggedUser { status, user ->
            if (status == FirebaseDbWorkerError.SUCCESS){
                Log.i("stdout", "Logged User loaded successfully")
                view?.displayUserProfile(user.username, user.occupation)
            }else{
                Log.i("stdout", "Logged User load failed!")
            }
        }
    }

}