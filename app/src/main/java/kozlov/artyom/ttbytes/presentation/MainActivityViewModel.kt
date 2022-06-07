package kozlov.artyom.ttbytes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kozlov.artyom.ttbytes.domain.entity.NewsItem
import kozlov.artyom.ttbytes.domain.usecases.GetNewsPagingListUseCase
import javax.inject.Inject
import kotlin.math.log

class MainActivityViewModel @Inject constructor(
    getNewsPagingListUseCase: GetNewsPagingListUseCase
) : ViewModel() {

    var newsFlow: Flow<PagingData<NewsItem>> = getNewsPagingListUseCase.invoke().cachedIn(viewModelScope)


    override fun onCleared() {
        newsFlow.cancellable()
        super.onCleared()
    }

}