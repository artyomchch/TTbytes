package kozlov.artyom.ttbytes.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kozlov.artyom.ttbytes.domain.entity.NewsItem
import kozlov.artyom.ttbytes.domain.repository.NewsListRepository
import javax.inject.Inject

class GetNewsPagingListUseCase @Inject constructor(private val newsListRepository: NewsListRepository) {

    operator fun invoke(): Flow<PagingData<NewsItem>> = newsListRepository.getPagedNews()

}