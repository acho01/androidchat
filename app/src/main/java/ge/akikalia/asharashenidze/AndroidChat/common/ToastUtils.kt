package ge.akikalia.asharashenidze.AndroidChat.common

import android.content.Context
import android.widget.Toast

object ToastUtils {

    @JvmStatic
    fun toast(message: String, context: Context?) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}