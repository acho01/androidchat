package ge.akikalia.asharashenidze.AndroidChat.model

class User{
    var userId: String? = null
    var occupation: String? = null
    var username: String? = null

    constructor()

    constructor(userId: String? = null,
                occupation: String? = null,
                username: String? = null
    ){
        this.userId = userId
        this.occupation = occupation
        this.username = username
    }
}