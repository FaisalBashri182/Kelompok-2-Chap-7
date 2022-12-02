package tsm.bdg.ch6group.ui.setting

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val PREFS_NAME = "soundgamesuit"
    private var sharedpref : SharedPreferences
    val editor : SharedPreferences.Editor

    init {
        sharedpref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        editor = sharedpref.edit()
    }

    fun putBoolean(key: String, value : Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String) : Boolean {
        return sharedpref.getBoolean(key, false)
    }
}