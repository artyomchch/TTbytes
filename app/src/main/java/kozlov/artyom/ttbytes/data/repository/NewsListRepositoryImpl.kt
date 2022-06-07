package kozlov.artyom.ttbytes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.ttbytes.data.mapper.NewsItemMapper
import kozlov.artyom.ttbytes.data.network.NewsApi
import kozlov.artyom.ttbytes.data.pagination.NewsPagingSource
import kozlov.artyom.ttbytes.domain.entity.NewsItem
import kozlov.artyom.ttbytes.domain.repository.NewsListRepository
import javax.inject.Inject


class NewsListRepositoryImpl @Inject constructor(
    private val mapper: NewsItemMapper,
    private val retrofit: NewsApi
) : NewsListRepository {


    override fun getPagedNews(): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(retrofit, mapper) }
        ).flow
    }

    fun refreshPagedNews(): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(retrofit, mapper) }
        ).flow
    }


}