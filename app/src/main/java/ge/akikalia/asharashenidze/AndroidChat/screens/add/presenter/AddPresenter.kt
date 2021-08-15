package ge.akikalia.asharashenidze.AndroidChat.screens.add.presenter

import ge.akikalia.asharashenidze.AndroidChat.data.firebase.storage.ChatStorage
import ge.akikalia.asharashenidze.AndroidChat.model.UserData
import ge.akikalia.asharashenidze.AndroidChat.screens.add.view.IAddView

class AddPresenter(private val view: IAddView){

    var userList: List<UserData>? = null

    fun onViewLoad() {
        view.startLoader()
        ChatStorage.getUserList { list->
            view.stopLoader(list == null)
            if (list != null) {
                view.updateList(list)
            }
        }
    }
//        ChatStorage.signOut()

//        ChatStorage.startListeningToChat("68702e4d-8c77-4267-85ad-d67d1c2c6f5f") { list ->
//            Log.i("stdout", "listing Chat Messages: ")
//            list?.forEach { message ->
//                with(message) {
//                    Log.i(
//                        "stdout",
//                        "id: $id, senderId: $senderId, timestamp: $timestamp, text: $text"
//                    )
//                }
//            }
//        }
//        ChatStorage.sendMessage("68702e4d-8c77-4267-85ad-d67d1c2c6f5f", "yooooo")
//
//        ChatStorage.sendMessage("68702e4d-8c77-4267-85ad-d67d1c2c6f5f", "yooooo0000000")
//
//        ChatStorage.sendMessage("68702e4d-8c77-4267-85ad-d67d1c2c6f5f", "yooooo0000000")


//        ChatStorage.startListeningToChatPreviewList { list ->
//            Log.i("stdout", "listing ChatPreviews: ")
//            list?.forEach { preview ->
//                with(preview) {
//                    Log.i(
//                        "stdout",
//                        "id: $id, username: $username, lastMessage: $lastMessage, timestamp: $timestamp"
//                    )
//                }
//            }
//        }

//        ChatStorage.changeOccupation("friendly guy")

//        ChatStorage.createChatWithUser("lowhDhgo3xXe62QGO1VVlBwq5lO2")

        //        Log.i("stdout", "am I signed in : ${ChatStorage.isSignedIn()}")

//        ChatStorage.getUserList { list->
//            Log.i("stdout", "listing users: ")
//            list?.forEach { user ->
//                Log.i("stdout", "id: ${user.id}, username: ${user.username}, occupation: ${user.occupation}")
//            }
//        }

//        ChatStorage.signIn("giorgi giorgadze", "giorgiPassword"){
//            Log.i("stdout", "sign in callback result: $it")
//        }

//        ChatStorage.signOut()

//        ChatStorage.signUp("giorgi giorgadze", "giorgiPassword", "laziness expert"){
//            Log.i("stdout", "sign up callback result: $it")
//        }
//    }

//    fun loadData() {
//        ChatStorage.getUserList { list ->
//            userList = list
//            if (list != null)
//                view.updateList(list)
//            else
//                view.displayError()
//        }
//    }

    fun search(text: String) {
        if (userList != null)
            view.updateList(userList!!.filter { user -> user.username.contains(text, true) })
    }

    fun addUser(id: String) {
        val chatId = ChatStorage.createChatWithUser(id)
        view.listItemClickedWithId(chatId)
        view.closeView()
    }
}