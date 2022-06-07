package kozlov.artyom.ttbytes.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kozlov.artyom.ttbytes.presentation.MainActivityViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    @Binds
    fun bindMainActivityViewModel(impl: MainActivityViewModel): ViewModel


}