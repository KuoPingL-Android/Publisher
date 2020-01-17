package students.jimmy.publisher.fragment_articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import students.jimmy.publisher.DataSource
import students.jimmy.publisher.dataclass.Article

class ArticlesViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _navigateToEditor = MutableLiveData<Boolean>()
    val navigateToEditor: LiveData<Boolean>
        get() = _navigateToEditor

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    init {
        startsListeningToData()
    }

    fun navigateToCreateArticle() {
        _navigateToEditor.value = true
    }

    fun onCreateArticleFragment() {
        _navigateToEditor.value = null
    }

    private fun startsListeningToData() {
        DataSource.articlesCollection.orderBy("createdTime", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->

            if (exception != null) {
                Log.d("ARTICLESVIEWMODEL", "FS EXCEPTION: $exception")
            } else {
                _articles.value = snapshot?.toObjects(Article::class.java)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}