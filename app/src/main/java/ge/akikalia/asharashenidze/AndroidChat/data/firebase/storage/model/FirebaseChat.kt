package ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.model

class FirebaseChat(val id: String = "", val lastMessage: String = "", var members: ArrayList<String> = arrayListOf(), val lastMessageTimestamp: Long = -1)