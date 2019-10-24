package simple.code.base

import android.app.Application
import com.squareup.picasso.Picasso
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import simple.code.base.utils.PreferencesUtils
import timber.log.Timber


open class BaseApplication : Application() {

    companion object {
        private val cicerone: Cicerone<Router> = Cicerone.create()

        fun getNavigatorHolder(): NavigatorHolder {
            return cicerone.navigatorHolder
        }

        fun getRouter(): Router {
            return cicerone.router
        }
    }

    protected lateinit var prefs: PreferencesUtils

    override fun onCreate() {
        super.onCreate()

        prefs = PreferencesUtils(this, packageName)


        //Setup Timber
        Timber.plant(Timber.DebugTree())

        //Setup Picasso
        Picasso.setSingletonInstance(
            Picasso.Builder(applicationContext).build()
//                .downloader(OkHttp3Downloader(OkHttpUnSafe().getOkHttpClientUnSafe().build()))
        )




    }

}