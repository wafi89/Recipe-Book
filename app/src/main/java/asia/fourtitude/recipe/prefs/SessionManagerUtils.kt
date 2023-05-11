package asia.fourtitude.recipe.prefs

import android.content.Context

object SessionManagerUtils {

    private const val SESSION_PREFERENCES = "asia.fourtitude.recipe.prefs.SESSION_PREFERENCES"
    private const val SESSION_FIRST_TIME_INSTALL = "asia.fourtitude.recipe.prefs.SESSION_FIRST_TIME_INSTALL"

    private fun clearStoredData(context: Context) {
        val editor = context.getSharedPreferences(SESSION_PREFERENCES, 0).edit()
        editor.clear()
        editor.apply()
    }

    // SESSION_USER_LOGIN_STATE
    fun setFirstTimeInstall(context: Context, value: Boolean) {
        val editor = context.getSharedPreferences(SESSION_PREFERENCES, 0).edit()
        editor.putBoolean(SESSION_FIRST_TIME_INSTALL, value)
        editor.apply()
    }

    fun getFirstTimeInstall(context: Context) : Boolean {
        return context.getSharedPreferences(SESSION_PREFERENCES, 0).getBoolean(SESSION_FIRST_TIME_INSTALL, true)
    }
}