package ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.db

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ge.akikalia.asharashenidze.AndroidChat.common.DispatchGroup
import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.model.*
import kotlin.collections.HashMap

object FirebaseDbWorker {
    private val database = FirebaseDatabase.getInstance()

    private val userChatsRef = database.getReference("UserChats")

    private val usersRef = database.getReference("Users")

    private val chatsRef = database.getReference("Chats")

    private val chatMessagesRef = database.getReference("ChatMessages")

    private var chatListListener: ValueEventListener? = null

    private var chatMessageListListener: ValueEventListener? = null

    fun listenForChatListChanges(userId: String, onComplete: (idNameMap: HashMap<String, FirebaseUserInfo>?,chatList: List<FirebaseChat>?) -> Unit) {
        stopListeningForChatListChanges()
        chatListListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userChats = snapshot.children.mapNotNull { snap ->
                    snap.getValue(String::class.java)
                }
                var group = DispatchGroup()
                var errorCode = false
                var values = ArrayList<FirebaseChat>()
                var idMap = HashMap<String, FirebaseUserInfo>()
                userChats.forEach { chatId ->
                    group.enter()
                    chatsRef.child(chatId)
                        .addListenerForSingleValueEvent(object : ValueEventListener {

                            override fun onDataChange(snapshot: DataSnapshot) {
                                val chat = snapshot.getValue(FirebaseChat::class.java)
                                if (chat != null) {
                                    chat.members = chat.members.filter { id -> id != userId } as ArrayList<String>
                                    synchronized(this) {
                                        values.add(chat)
                                    }
                                    val id = chat.members[0]
                                    usersRef.child(id).addListenerForSingleValueEvent(object :
                                        ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val user = snapshot.getValue(FirebaseUserInfo::class.java)
                                            if (user != null)
                                                synchronized(this){
                                                    idMap[id] = user
                                                }
                                            else
                                                synchronized(this){
                                                    errorCode = true
                                                }
                                            group.leave()
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            synchronized(this) {
                                                errorCode = true
                                            }
                                        }
                                    })
                                }else
                                    synchronized(this) {
                                        errorCode = true
                                    }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                synchronized(this) {
                                    errorCode = true
                                }
                                group.leave()
                            }

                        })
                }
                group.notify {
                    if (errorCode)
                        onComplete(null, null)
                    else
                        onComplete(idMap, values)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("stdout", "Failed to read value.", error.toException())
                onComplete(null, null)
            }
        }
        userChatsRef.child(userId).addValueEventListener(chatListListener!!)
    }

    fun stopListeningForChatListChanges() {
        chatListListener?.let { listener ->
            userChatsRef.removeEventListener(listener)
        }
    }

    fun listenForChatMessages(
        chatId: String,
        onComplete: (List<FirebaseChatMessage>?) -> Unit
    ) {
        stopListeningForChatMessages()
        chatMessageListListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatMessages = snapshot.children.mapNotNull { snap ->
                    snap.getValue(FirebaseChatMessage::class.java)
                }
                onComplete(chatMessages)
            }

            override fun onCancelled(error: DatabaseError) {
                onComplete(null)
            }
        }
        chatMessagesRef.child(chatId).addValueEventListener(chatMessageListListener!!)
    }

    fun stopListeningForChatMessages() {
        chatMessageListListener?.let { listener ->
            chatMessagesRef.removeEventListener(listener)
        }
    }

    fun getUserInfo(userId: String, onComplete: (FirebaseUserInfo?) -> Unit) {
        usersRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(FirebaseUserInfo::class.java)
                onComplete(user)
            }

            override fun onCancelled(error: DatabaseError) {
                onComplete(null)
            }
        })
    }

    fun getUserInfoList(onComplete: (List<FirebaseUserInfo>?) -> Unit) {
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var users = snapshot.children.map { snap ->
                    snap.getValue(FirebaseUserInfo::class.java)
                }
                onComplete(users.filterNotNull())
            }

            override fun onCancelled(error: DatabaseError) {
                onComplete(null)
            }
        })
    }

    fun createChat(chat: FirebaseChat) { // if chat does not exist already
        chatsRef.child(chat.id).setValue(chat)
        chatMessagesRef.child(chat.id).setValue(null)//(ArrayList<FirebaseChatMessage>())
        userChatsRef.child(chat.members[0]).push().setValue(chat.id)
        userChatsRef.child(chat.members[1]).push().setValue(chat.id)
    }

    fun createChatMessage(chatId: String, message: FirebaseChatMessage) {
        chatMessagesRef.child(chatId).child(message.id).setValue(message)
        chatsRef.child(chatId).child("lastMessage").setValue(message.text)
        chatsRef.child(chatId).child("lastMessageTimestamp").setValue(message.timestamp)
    }

    fun createUserInfo(user: FirebaseUserInfo) {
        usersRef.child(user.id).setValue(user)
        userChatsRef.child(user.id).setValue(null)//(ArrayList<FirebaseUserChat>())
    }

    fun updateUserProfile(userId: String, occupation: String, username: String) {
        usersRef.child(userId).child("occupation").setValue(occupation)
        usersRef.child(userId).child("username").setValue(username)
    }
}