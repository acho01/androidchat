package ge.akikalia.asharashenidze.AndroidChat.model

class UserChat{
    var chatId: String? = null
    var lastMessageTime: String? = null
    var partnerUsername: String? = null
    var lastMessage: String? = null

    constructor(chatId: String? = null,
                lastMessageTime: String? = null,
                partnerUsername: String? = null,
                lastMessage: String? = null
    ){
        this.chatId = chatId
        this.lastMessage = lastMessage
        this.lastMessageTime = lastMessageTime
        this.partnerUsername = partnerUsername
    }
}