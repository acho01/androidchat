package ge.akikalia.asharashenidze.AndroidChat.data.firebase

interface FirebaseWorkerDelegate {
    fun firebaseWorkerDidSignUpUser(sender: FirebaseWorker, error: FirebaseWorkerError)
    fun firebaseWorkerDidSignInUser(sender: FirebaseWorker, error: FirebaseWorkerError)
}