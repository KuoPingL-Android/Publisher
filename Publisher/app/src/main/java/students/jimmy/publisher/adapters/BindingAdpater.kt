package students.jimmy.publisher.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import students.jimmy.publisher.dataclass.Article

@BindingAdapter("articles")
fun bind(recyclerView: RecyclerView, articles: List<Article>?) {
    (recyclerView.adapter as? ArticlesAdapter)?.submitList(articles)
}