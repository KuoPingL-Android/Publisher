package students.jimmy.publisher.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import students.jimmy.publisher.databinding.ItemArticleBinding
import students.jimmy.publisher.dataclass.Article

class ArticlesAdapter: ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(ArticleCallback) {

    companion object ArticleCallback: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return (oldItem.author.authorID == newItem.author.authorID
                    && oldItem.articleID == newItem.articleID)
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    class ArticleViewHolder(val binding: ItemArticleBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}