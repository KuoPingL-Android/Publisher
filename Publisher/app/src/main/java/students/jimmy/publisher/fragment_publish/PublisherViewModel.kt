package students.jimmy.publisher.fragment_publish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import students.jimmy.publisher.DataSource
import students.jimmy.publisher.dataclass.Article
import students.jimmy.publisher.dataclass.Author

class PublisherViewModel : ViewModel() {
    val title = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val isArticlePrepared = MediatorLiveData<Boolean>().apply {
        addSource(title)    { checkIfArticleIsReadyForPublish() }
        addSource(tag)      { checkIfArticleIsReadyForPublish() }
        addSource(content)  { checkIfArticleIsReadyForPublish() }
    }

    private val _articleSent = MutableLiveData<Boolean>()
    val articleSent: LiveData<Boolean>
        get() = _articleSent

    private val _discardArticle = MutableLiveData<Boolean>()
    val discardArticle: LiveData<Boolean>
        get() = _discardArticle

    fun checkIfArticleIsReadyForPublish(){
        isArticlePrepared.value =
            !((content.value == null || content.value?.trim()?.isEmpty() == true) ||
                (tag.value == null || tag.value?.trim()?.isEmpty() == true) ||
                (title.value == null || title.value?.trim()?.isEmpty() == true))
    }

    fun addData() {
        val author = Author("jimmyEmail@gmail.com", "jimmy", "Jimmy")
        val article = Article(author, title.value!!, content.value!!, tag = tag.value!!)
        DataSource.addData(article)
        _articleSent.value = true
    }

    fun resetArticleSent() {
        _articleSent.value = null
    }

    fun closeDialog() {
        _discardArticle.value = true
    }

    fun resetDiscardArticle() {
        _discardArticle.value = null
    }

}