package com.umdaa.nurseasst.Activities

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class HomeScreenObserver : LifecycleObserver {


    private val TAG = this.javaClass.getSimpleName()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent(){
        Log.v(TAG, "Observe on Create")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent() {
        Log.i(TAG, "ON_DESTROY event")
    }
}