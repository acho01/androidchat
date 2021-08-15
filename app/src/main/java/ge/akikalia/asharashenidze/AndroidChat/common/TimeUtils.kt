package ge.akikalia.asharashenidze.AndroidChat.common

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    @JvmStatic
    fun format(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val netDate = Date(timestamp)
        return sdf.format(netDate)
    }
}