package kozlov.artyom.ttbytes.data.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kozlov.artyom.ttbytes.data.mapper.NewsItemMapper
import kozlov.artyom.ttbytes.data.network.NewsApi
import kozlov.artyom.ttbytes.domain.entity.NewsItem


class NewsPagingSource(
    private val loader: NewsApi,
    private val mapper: NewsItemMapper,
) : PagingSource<Int, NewsItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        val pageIndex = params.key ?: 1
        Log.d("page", "load:  $pageIndex")

        return try {
            val news = mapper.mapListNetworkModelToListEntityNews(loader.getPostPaging(page = pageIndex, pageSize = params.loadSize).article)
            return LoadResult.Page(
                data = news,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (news.isEmpty()) null else if (pageIndex == 1) pageIndex + 3 else pageIndex + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}

