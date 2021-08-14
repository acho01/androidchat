package ge.akikalia.asharashenidze.AndroidChat.common

class UsernameUtils {
    companion object {
        fun mapToEmail(username: String): String{
            return "${username.replace(" ", "+")}@ako.acho.freeuni.edu.ge"
        }

        fun mapToUsername(email: String): String{
            return email.split('@')[0].replace("+", " ")
        }
    }
}