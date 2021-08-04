package ge.akikalia.asharashenidze.AndroidChat.common

import com.google.firebase.database.DataSnapshot
import ge.akikalia.asharashenidze.AndroidChat.model.UserChat

object UserChatUtils {

    @JvmStatic
    fun getChatPreviews(snapshot: DataSnapshot): List<UserChat>? {
        val userChats = mutableListOf<UserChat>()
        for (entry in (snapshot.value as HashMap<*, *>).entries) {
            val chatId = entry.key.toString()
            val childEntries = entry.value as HashMap<*, *>
            val lastMessage = childEntries.get("last_message").toString()
            val lastTimestamp = childEntries.get("last_timestamp").toString()
            val partner = childEntries.get("partner").toString()
            val userChat = UserChat(chatId, lastTimestamp, partner, lastMessage)
            userChats.add(userChat)
        }

        return userChats
    }

}