package kozlov.artyom.ttbytes.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.ttbytes.domain.entity.NewsItem


interface NewsListRepository {

    fun getPagedNews(): Flow<PagingData<NewsItem>>
}