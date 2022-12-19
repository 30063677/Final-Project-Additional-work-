package com.umdaa.nurseasst.Commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.*

object Constants {


    val PDUS = "pdus"
    val ANDROID = "Android"
    val SUCCESS = 200
    val KEY_1 = "key1"
    val CONNECTION_TIME_OUT: Long = 60
    val READ_TIME_OUT: Long = 60
    val WRITE_TIME_OUT: Long = 60
    val ZOOM_LEVEL = 17
    val MESSAGE = "message"
    val CODE = "code"
    val DESCRIPTION = "description"
    val FILE_NAME = "MobileApi/CV1"


    val INTERNET_UNABLEABLE = "Not connected to the internet. Please check your connection and try again."
    val CUSTOMER = "customer"
    val UPDATE = "UPDATE"
    val DELETE = "DELETE"
    val ADD = "ADD"
    val NO_INTERNET_CONNECTION_MESSAGE = "Please DoctorProfiles to Internet.\nAnd Try Again."
    val UNABLE_TO_CONNECT_OUR_SERVER =
        "Unable to DoctorProfiles Our Server.\nPlease Try Again Later."
    val ENCRYPTION_KEY = "TATX.KSA"
    val MOBILE = "mobile"





    fun isConnectingToInternet(context: Context): Boolean {
        val connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info)
                    if (i.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false
    }

}