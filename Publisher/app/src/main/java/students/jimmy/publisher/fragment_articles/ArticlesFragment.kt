package students.jimmy.publisher.fragment_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import students.jimmy.publisher.adapters.ArticlesAdapter
import students.jimmy.publisher.databinding.FragmentArticlesBinding
import students.jimmy.publisher.factory.GeneralViewModelFactory

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private val articleAdpater = ArticlesAdapter()
    private val viewModel by lazy {
        GeneralViewModelFactory().create(ArticlesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.recyclerArticles.adapter = articleAdpater
        binding.viewModel = viewModel

        return binding.root
    }
}