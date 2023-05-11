package asia.fourtitude.recipe
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import asia.fourtitude.recipe.utils.exception.ExceptionListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), ExceptionListener {

    override fun onCreate() {
        super.onCreate()
        setupExceptionHandler()
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        Log.d("FourtitudeRecipeApp", throwable.message.toString())
    }

    private fun setupExceptionHandler(){
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    uncaughtException(Looper.getMainLooper().thread, e)
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            uncaughtException(t, e)
        }
    }

}