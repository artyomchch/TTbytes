package kozlov.artyom.ttbytes.data.mapper

import android.annotation.SuppressLint
import kozlov.artyom.ttbytes.data.network.pojo.ArticleDto
import kozlov.artyom.ttbytes.domain.entity.NewsItem
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsItemMapper @Inject constructor() {


    private fun mapNetworkModelToEntityNews(article: ArticleDto) = NewsItem(
        url = article.url,
        imageUrl = article.urlToImage,
        author = article.author,
        title = article.title,
        description = article.description,
        source = article.source.name,
        time = dateToTimeFormat(article.publishedAt),
        date = dateFormat(article.publishedAt)
    )

    fun mapListNetworkModelToListEntityNews(list: List<ArticleDto>) =
        list.map {
            mapNetworkModelToEntityNews(it)
        }


    fun dateToTimeFormat(oldStringDate: String?): String? {
        val p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH
            )
            val date: Date = sdf.parse(oldStringDate!!)!!
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(oldStringDate: String?): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
        newDate = try {
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate!!)!!
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldStringDate
        }
        return newDate
    }

    private fun getCountry(): String {
        val locale = Locale.getDefault()
        val country = java.lang.String.valueOf(locale.country)
        return country.lowercase(Locale.getDefault())
    }
}