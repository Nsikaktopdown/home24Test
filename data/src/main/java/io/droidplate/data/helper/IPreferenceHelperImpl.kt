package io.droidplate.data.helper

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * This class handles sharedPreference  data caching
 * @param Context application context
 */

class IPreferenceHelperImpl @Inject constructor(context: Context) : IPreferenceHelper {

    private val basePref: SharedPreferences


    init {

        basePref = context.getSharedPreferences(PREF_BASE_PACKAGE_NAME, Context.MODE_PRIVATE)

    }

    /**
     * Save user login preference
     */
    override var isLoggedIn: Boolean
        get() = basePref.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) {
            basePref.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
        }

    companion object {

        private val PREF_BASE_PACKAGE_NAME = "io.altalabs.android.preferences"
        private val KEY_IS_LOGGED_IN = "is_logged_in"
    }

}