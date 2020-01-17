package students.jimmy.publisher.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import students.jimmy.publisher.fragment_articles.ArticlesViewModel
import students.jimmy.publisher.fragment_publish.PublisherViewModel

class GeneralViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(ArticlesViewModel::class.java) ->
                    ArticlesViewModel()
                isAssignableFrom(PublisherViewModel::class.java) ->
                    PublisherViewModel()
                else -> {
                    throw ClassNotFoundException("No Class is found in Factory")
                }
            }
        } as T
    }

}