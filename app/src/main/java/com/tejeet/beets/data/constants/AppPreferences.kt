package com.tejeet.beets.data.constant

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "TikTokPREF"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    private val VIBRATION_OFF : Boolean = false
    private val SOUND_OFF : Boolean = false

    private val USER_FIRST_TIME_KEY = "user_first_time_key"
    private val USER_LOGIN_STATUS_KEY = "user_login_key"

    private val VIBRATION_STATUS_KEY = "user_vibration_status_key"
    private val SOUND_STATUS_KEY = "sound_status_key"
    private val USER_NAME_KEY = "user_name_key"
    private val USER_DISPLAY_NAME_KEY = "user_display_name_key"
    private val USER_EMAIL_KEY = "user_email_key"
    private val USER_PROFILE_URL_KEY = "user_profile_url_key"
    private val USER_PASSWORD_KEY = "user_password_key"
    private val USER_MOBILE_KEY = "user_mobile_key"
    private val USER_INTREST_KEY = "user_intrest_key"
    private val USER_AUTH_KEY = "user_auth_key"
    private val USER_FREBASE_TOKEN_KEY = "user_firebase_token_key"
    private val USER_FIREBASE_TOKEN_UPDATE_STATUS = "user_firebase_token_updtate_status"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }


    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    fun setUser(displayName : String ,pName : String, pEmail : String, pProfileURL : String){
        val editor = preferences.edit()
        editor.putString(USER_DISPLAY_NAME_KEY, displayName)
        editor.putString(USER_NAME_KEY,pName)
        editor.putString(USER_EMAIL_KEY,pEmail)
        editor.putString(USER_PROFILE_URL_KEY,pProfileURL)
        editor.apply()
    }

    var userDisplayName : String?
        get() = preferences.getString(USER_DISPLAY_NAME_KEY, "-")
        set(value) = preferences.edit {
            it.putString(USER_DISPLAY_NAME_KEY, value)
        }

    var userName : String?
        get() = preferences.getString(USER_NAME_KEY, "-")
        set(value) = preferences.edit {
            it.putString(USER_NAME_KEY, value)
        }

    var userEmail : String?
        get() = preferences.getString(USER_EMAIL_KEY, "-")
        set(value) = preferences.edit {
            it.putString(USER_EMAIL_KEY, value)
        }

    var userProfile : String?
        get() = preferences.getString(USER_PROFILE_URL_KEY, "-")
        set(value) = preferences.edit {
            it.putString(USER_PROFILE_URL_KEY, value)
        }


    var userFirebaseToken : String?
        get() = preferences.getString(USER_FREBASE_TOKEN_KEY, "0")
        set(value) = preferences.edit {
            it.putString(USER_FREBASE_TOKEN_KEY, value)
        }

    var isTokenUpdated : String?
        get() = preferences.getString(USER_FIREBASE_TOKEN_UPDATE_STATUS, "NO")
        set(value) = preferences.edit {
            it.putString(USER_FIREBASE_TOKEN_UPDATE_STATUS, value)
        }

    var is_FirstTime : String?
        get() = preferences.getString(USER_FIRST_TIME_KEY, "YES")
        set(value) = preferences.edit {
            it.putString(USER_FIRST_TIME_KEY, value)
        }


    var isLoggedIn : String?
        get() = preferences.getString(USER_LOGIN_STATUS_KEY, "NO")
        set(value) = preferences.edit {
            it.putString(USER_LOGIN_STATUS_KEY, value)

        }


    var userIntrests : String?
        get() = preferences.getString(USER_INTREST_KEY, "General")
        set(value) = preferences.edit {
            it.putString(USER_INTREST_KEY, value)

        }

    var is_vibration :Boolean
        get() = preferences.getBoolean(VIBRATION_STATUS_KEY.toString(), VIBRATION_OFF)
        set(value) = preferences.edit {
            it.putBoolean(VIBRATION_STATUS_KEY.toString(), value)

        }


    var is_sound :Boolean
        get() = preferences.getBoolean(SOUND_STATUS_KEY.toString(), SOUND_OFF)
        set(value) = preferences.edit {
            it.putBoolean(SOUND_STATUS_KEY.toString(), value)

        }

}