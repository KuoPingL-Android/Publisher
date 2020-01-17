package students.jimmy.publisher

import android.app.Application
import android.content.Context

class PublisherApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}