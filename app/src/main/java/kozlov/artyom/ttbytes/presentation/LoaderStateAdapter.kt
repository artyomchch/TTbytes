package kozlov.artyom.ttbytes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import kozlov.artyom.ttbytes.R
import kozlov.artyom.ttbytes.databinding.ItemLoaderBinding


typealias TryAgainAction = () -> Unit

class LoaderStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {


    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = ItemLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding, null, tryAgainAction)
    }


    class LoaderViewHolder(
        private val binding: ItemLoaderBinding, private val shimmerLayout: ShimmerFrameLayout?, private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {


            animationView.isVisible = loadState is LoadState.Error
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            if (loadState is LoadState.Loading) {
                shimmerLayout?.visibility = View.VISIBLE
            } else shimmerLayout?.visibility = View.GONE


            if (shimmerLayout != null) {
                progressBar.isVisible = false
            } else {
                progressBar.isVisible = loadState is LoadState.Loading
                animationView.isVisible = loadState is LoadState.Loading
                messageTextView.text = messageTextView.context.resources.getString(R.string.request_error)
                tryAgainButton.visibility = View.INVISIBLE


            }
        }

    }
}
