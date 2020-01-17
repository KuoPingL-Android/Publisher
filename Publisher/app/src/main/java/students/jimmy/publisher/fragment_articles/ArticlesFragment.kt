package students.jimmy.publisher.fragment_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import students.jimmy.publisher.NavigationDirections
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

        articleAdpater.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    binding.recyclerArticles.smoothScrollToPosition(0)
                }
            }
        })

        binding.viewModel = viewModel

        viewModel.navigateToEditor.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.actionGlobalPublisherFragment())
                viewModel.onCreateArticleFragment()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}