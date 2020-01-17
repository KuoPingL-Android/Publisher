package students.jimmy.publisher.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.HashMap

@Parcelize
data class Article(
    val author: Author = Author(),
    val title: String = "",
    val content: String = "",
    val createdTime: Long = Calendar.getInstance().timeInMillis,
    var articleID: String = "",
    val tag: String = ""
):Parcelable {

    @IgnoredOnParcel
    val formatter = SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.getDefault())

    val data: HashMap<String, Any>
        get() {
            return hashMapOf (
                "author" to
                        hashMapOf (
                            "email" to author.email ,
                            "id" to author.authorID ,
                            "name" to author.name) ,
                "title" to title ,
                "content" to content ,
            "createdTime" to createdTime ,
            "id" to articleID ,
            "tag" to tag
            )
        }

    val dateString: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = createdTime
            return formatter.format(calendar.time)
        }
}