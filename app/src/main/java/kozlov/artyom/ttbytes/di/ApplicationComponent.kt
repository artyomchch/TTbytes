package kozlov.artyom.ttbytes.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kozlov.artyom.ttbytes.presentation.MainActivity

@ApplicationScope
@Component(
    modules = [ViewModelModule::class, NewsDomainModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}