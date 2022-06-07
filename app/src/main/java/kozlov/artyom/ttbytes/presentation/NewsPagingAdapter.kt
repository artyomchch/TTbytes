package kozlov.artyom.ttbytes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import kozlov.artyom.ttbytes.R
import kozlov.artyom.ttbytes.databinding.NewsItemBinding
import kozlov.artyom.ttbytes.domain.entity.NewsItem


class NewsPagingAdapter : PagingDataAdapter<NewsItem, NewsItemViewHolder>(NewsItemDiffCallback()) {


    override fun onBindViewHolder(viewHolder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position) ?: return
        with(viewHolder.binding) {
            Glide.with(root)
                .load(newsItem.imageUrl)
                .placeholder(R.drawable.ic_broken_image)
                .centerCrop()
                .into(imageUrl)

            title.text = newsItem.title
            source.text = newsItem.source
            timeAgo.text = newsItem.time


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }
}