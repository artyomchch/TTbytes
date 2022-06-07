package kozlov.artyom.ttbytes.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import kozlov.artyom.ttbytes.data.network.NewsApi
import kozlov.artyom.ttbytes.data.network.RetrofitInstance
import kozlov.artyom.ttbytes.data.repository.NewsListRepositoryImpl
import kozlov.artyom.ttbytes.domain.repository.NewsListRepository

@Module
interface NewsDomainModule {

    @ApplicationScope
    @Binds
    fun bindNewsListRepository(impl: NewsListRepositoryImpl): NewsListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideAppInternet(): NewsApi {
            return RetrofitInstance.api
        }
    }
}