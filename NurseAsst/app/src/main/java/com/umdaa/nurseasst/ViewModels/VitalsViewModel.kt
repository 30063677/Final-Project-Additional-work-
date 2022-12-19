package com.umdaa.nurseasst.ViewModels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.umdaa.nurseasst.Database.CommonDao
import com.umdaa.nurseasst.Database.NurseDatabase
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.Objects.Vitals

class VitalsViewModel (application: Application, val id : Int) : AndroidViewModel(application)  {

    private val TAG = this.javaClass.getSimpleName()
    private val commonDao: CommonDao
    private val database: NurseDatabase?
    internal val allPatientVitals: LiveData<List<Vitals>>

    init {

        database = NurseDatabase.getDatabase(application)
        commonDao = database!!.commonDao()
        allPatientVitals = commonDao.getVitals(id)
    }

    fun insert(vitals : Vitals) {
        InsertAsyncTask(commonDao).execute(vitals)
    }


    fun delete(vitals: Vitals) {
        DeleteAsyncTask(commonDao).execute(vitals)
    }


    private open inner class OperationAsyncTask internal constructor(internal var mAsyncTask: CommonDao) :
        AsyncTask<Vitals, Void, Void>() {
        override fun doInBackground(vararg vitals: Vitals): Void? {
            return null
        }

    }

    private open inner class InsertAsyncTask internal constructor(commonDao: CommonDao) :
        OperationAsyncTask(commonDao) {
        override fun doInBackground(vararg vitals: Vitals): Void? {
            mAsyncTask.insertVitals(vitals[0])
            return null
        }
    }

    private open inner class DeleteAsyncTask internal constructor(commonDao: CommonDao) :
        OperationAsyncTask(commonDao) {

        override fun doInBackground(vararg vitals: Vitals): Void? {
            mAsyncTask.delete(vitals[0])
            return null
        }
    }

    fun heartRate(heart_rate: Int): Int {
        var score: Int? = null
        if (heart_rate in 51..90) {
            score = 0
        } else if (heart_rate in 41..50 || heart_rate in 91..100) {
            score = 1
        } else if (heart_rate in 111..130) {
            score = 2
        } else if (heart_rate <= 40 || heart_rate >= 131) {
            score = 3
        }
        return score!!
    }


}