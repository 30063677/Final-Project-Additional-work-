package com.umdaa.nurseasst.SharedPref

import android.content.Context
import com.umdaa.nurseasst.Objects.NurseDetails

class SharedPrefManager  private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val nurseDetails: NurseDetails
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return NurseDetails(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getString("name", null)!!,
                sharedPreferences.getString("clinic", null)!!
            )
        }


    fun saveUser(nurseDetails: NurseDetails) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", nurseDetails.nurse_id)
        editor.putString("email", nurseDetails.nurse_email)
        editor.putString("name", nurseDetails.nurse_name)
        editor.putString("clinic", nurseDetails.clinic)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}