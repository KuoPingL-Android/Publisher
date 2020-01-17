package students.jimmy.publisher

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import students.jimmy.publisher.dataclass.Article
import students.jimmy.publisher.dataclass.Author
import java.util.*

object DataSource {
    private val db = FirebaseFirestore.getInstance()

    private const val COLLECTION_ARTICLES = "articles"
    private const val TAG = "DATASOURCE"
    private lateinit var dummyTimer: Timer

    val articlesCollection = db.collection(COLLECTION_ARTICLES)

    fun prepareDummyData() {
        dummyTimer = Timer()
        dummyTimer.schedule(object :TimerTask(){
            var counter = 0
            override fun run() {
                if (counter == 10) {
                    donePreparing()
                }
                val author = Author("wayne@school.appworks.tw", "waynechen323", "AKA小安老師")
                val article = Article(author, " IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
                    "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，" +
                            "曾再跟工作人員的互動影片中坦言 自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」" +
                            "超復古穿搭 造型，卻意外美出新境界。", tag = "Beauty")
                addData(article)
                counter += 1
            }
        }, 5000, 5000)
    }

    private fun donePreparing() {
        dummyTimer.cancel()
    }

    fun addData(article: Article) {
        val document = articlesCollection.document()
        article.articleID = document.id
        document.set(article.data)
    }

    suspend fun fetchAllData(): List<Article>? {

        return withContext(Dispatchers.IO) {
            var articles = listOf<Article>()
            articlesCollection.orderBy("createdTime").get()
                .addOnSuccessListener {documents ->
                    if (documents != null) {
                        articles = documents.toObjects(Article::class.java)
                    } else {
                        // nothing
                    }
                }
                .addOnFailureListener {exception ->
                    Log.d(TAG, "EXCEPTION=${exception}")
                }

            return@withContext articles
        }
    }
}