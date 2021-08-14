package ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage

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

    private fun newId() = UUID.randomUUID().toString()

    private fun currTime() = System.currentTimeMillis()

    fun startListeningToChatPreviewList(onComplete: (chatList: List<ChatPreview>?) -> Unit) {
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null)
            FirebaseDbWorker.listenForChatListChanges(userId) { map, list ->
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
                }
                onComplete(newList)
            }
        else
            onComplete(null)
    }

    fun stopListeningToChatPreviewList() {
        FirebaseDbWorker.stopListeningForChatListChanges()
    }

    fun startListeningToChat(chatId: String, onComplete: (chatMessages: List<Message>?) -> Unit) {
        FirebaseDbWorker.listenForChatMessages(chatId) { list ->
            val newList = list?.map { with(it) { Message(id, sender, timestamp, text) } }
            onComplete(newList)
        }
    }

    fun stopListeningToChat() {
        FirebaseDbWorker.stopListeningForChatMessages()
    }

    fun getUserData(onComplete: (user: UserData?) -> Unit) {
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
        FirebaseAuthWorker.signOut()
    }

    fun signIn(username: String, password: String, onComplete: (success: Boolean) -> Unit) {
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
        val userId = FirebaseAuthWorker.getUser()?.uid
        if (userId != null) {
            val message = FirebaseChatMessage(newId(), text, currTime(), userId)
            FirebaseDbWorker.createChatMessage(chatId, message)
        }
    }

    fun getUserList(onComplete: (userList: List<UserData>?) -> Unit) {
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

    fun createChatWithUser(userId: String) {
        val myId = FirebaseAuthWorker.getUser()?.uid
        if (myId != null) {
            val chat = FirebaseChat(newId(), "", arrayListOf(myId, userId), currTime())
            FirebaseDbWorker.createChat(chat)
        }
    }

    fun changeOccupation(newOccupation: String) {
        val myId = FirebaseAuthWorker.getUser()?.uid
        if (myId != null) {
            FirebaseDbWorker.updateUserOccupation(myId, newOccupation)
        }
    }
}