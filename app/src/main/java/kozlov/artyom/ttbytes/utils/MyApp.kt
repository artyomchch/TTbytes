package kozlov.artyom.ttbytes.utils

import android.app.Application
import kozlov.artyom.ttbytes.di.DaggerApplicationComponent

class MyApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}