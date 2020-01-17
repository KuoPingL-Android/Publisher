package students.jimmy.publisher.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val email: String = "",
    val authorID: String = "",
    val name: String = ""
):Parcelable