package ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage

import android.util.Log
import ge.akikalia.asharashenidze.AndroidChat.common.UsernameUtils
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.auth.FirebaseAuthWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db.FirebaseDbWorker
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.model.FirebaseChat
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.model.FirebaseChatMessage
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.model.FirebaseUserInfo
import ge.akikalia.asharashenidze.AndroidChat.model.ChatPreview
import ge.akikalia.asharashenidze.AndroidChat.model.Message
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import java.util.*

object ChatStorage {
    fun isSignedIn() = FirebaseAuthWorker.getUser() != null

    fun getUserId() = FirebaseAuthWorker.getUser()?.uid

    private fun newId() = UUID.randomUUID().toString()

    private fun currTime() = System.currentTimeMillis()

    fun startListeningToChatPreviewList(onComplete: (chatList: List<ChatPreview>?) -> Unit) {
        Log.i("stdout", "startListeningToChatPreviewList()")
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null)
            FirebaseDbWorker.listenForChatListChanges(userId) { map, list ->
                Log.i("stdout", "-->newList")
                val newList = list?.mapNotNull { chat ->
                    with(chat) {
                        val respondentId = members[0]
                        val username = map?.get(respondentId)?.username
                        if (username != null)
                            ChatPreview(
                                id,
                                username,
                                respondentId,
                                lastMessage,
                                lastMessageTimestamp
                            )
                        else
                            null
                    }
                }?.sortedByDescending { it -> it.timestamp }
                onComplete(newList)
            }
        else
            onComplete(null)
    }

    fun stopListeningToChatPreviewList() {
        Log.i("stdout", "stopListeningToChatPreviewList()")
        FirebaseDbWorker.stopListeningForChatListChanges()
    }

    fun startListeningToChat(chatId: String, onComplete: (chatMessages: List<Message>?) -> Unit) {
        Log.i("stdout", "startListeningToChat()")
        FirebaseDbWorker.listenForChatMessages(chatId) { list ->
            val newList = list?.map { with(it) { Message(id, sender, timestamp, text) } }?.sortedBy { it -> it.timestamp }
            onComplete(newList)
        }
    }

    fun stopListeningToChat() {
        Log.i("stdout", "stopListeningToChat()")
        FirebaseDbWorker.stopListeningForChatMessages()
    }

    fun getUserData(onComplete: (user: UserData?) -> Unit) {
        Log.i("stdout", "getUserData()")
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null) {
            FirebaseDbWorker.getUserInfo(userId) { user ->
                val newUser = user?.let { with(it) { UserData(id, username, occupation) } }
                onComplete(newUser)
            }
        } else
            onComplete(null)
    }

    fun signOut() {
        Log.i("stdout", "signOut()")
        FirebaseAuthWorker.signOut()
    }

    fun signIn(username: String, password: String, onComplete: (success: Boolean) -> Unit) {
        Log.i("stdout", "signIn()")
        val email = UsernameUtils.mapToEmail(username)
        FirebaseAuthWorker.signInUser(email, password) { firebaseUser ->
            if (firebaseUser != null) {
                onComplete(true)
            } else {
                onComplete(false)
            }
        }
    }

    fun signUp(
        username: String,
        password: String,
        occupation: String,
        onComplete: (success: Boolean) -> Unit
    ) {
        Log.i("stdout", "signUp()")
        val email = UsernameUtils.mapToEmail(username)
        FirebaseAuthWorker.signUpUser(email, password) { firebaseUser ->
            if (firebaseUser != null) {
                val user = FirebaseUserInfo(firebaseUser.uid, username, occupation)
                FirebaseDbWorker.createUserInfo(user)
                onComplete(true)
            } else {
                onComplete(false)
            }
        }
    }

    fun sendMessage(chatId: String, text: String) {
        Log.i("stdout", "sendMessage()")
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null) {
            val message = FirebaseChatMessage(newId(), text, currTime(), userId)
            FirebaseDbWorker.createChatMessage(chatId, message)
        }
    }

    fun getUserList(onComplete: (userList: List<UserData>?) -> Unit) {
        Log.i("stdout", "getUserList()")
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null) {
            FirebaseDbWorker.getUserInfoList { list ->
                var newList = list?.map { user ->
                    with(user) {
                        UserData(id, username, occupation)
                    }
                }
                newList = newList?.filter { user -> user.id != userId }
                onComplete(newList)
            }
        }
    }

    fun createChatWithUser(userId: String): String {
        Log.i("stdout", "createChatWithUser()")

        val myId = FirebaseAuthWorker.getUser()?.uid
        var chatId = ""
        if (myId != null) {
            chatId = newId()
            val chat = FirebaseChat(chatId, "", arrayListOf(myId, userId), currTime())
            FirebaseDbWorker.createChat(chat)
        }
        return chatId
    }

    fun changeProfile(newUsername: String, newOccupation: String, onComplete: (success: Boolean) -> Unit){
        Log.i("stdout", "changeProfile()")
        val user = FirebaseAuthWorker.getUser()
        if (user != null)
            user.updateEmail(UsernameUtils.mapToEmail(newUsername))
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        FirebaseDbWorker.updateUserProfile(user.uid, newOccupation, newUsername)
                        onComplete(true)
                    }else
                        onComplete(false)
                }
        else
            onComplete(false)
    }
}