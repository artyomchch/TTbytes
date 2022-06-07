package kozlov.artyom.ttbytes.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kozlov.artyom.ttbytes.databinding.ActivityMainBinding
import kozlov.artyom.ttbytes.utils.MyApp
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[(MainActivityViewModel::class.java)]
    }

    private val component by lazy {
        (application as MyApp).component
    }

    private lateinit var newsPagingAdapter: NewsPagingAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter
    private lateinit var mainLoadStateHolder: LoaderStateAdapter.LoaderViewHolder



    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        setContentView(binding.root)


    }


    private fun setupRecyclerView() {
        newsPagingAdapter = NewsPagingAdapter()
        val tryAgainAction: TryAgainAction = { newsPagingAdapter.retry() }
        loaderStateAdapter = LoaderStateAdapter(tryAgainAction)
        binding.listRecyclerView.apply {
            adapter = newsPagingAdapter.withLoadStateFooter(loaderStateAdapter)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        mainLoadStateHolder = LoaderStateAdapter.LoaderViewHolder(
            binding.loadStateView,
            binding.shimmerLayout,
            tryAgainAction
        )



        observeNews()
        observeLoadState()

    }

    private fun observeNews() {
        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest { pagingData ->
                Log.d("news", "observeNews: $pagingData")
                newsPagingAdapter.submitData(pagingData)

            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            newsPagingAdapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }


}