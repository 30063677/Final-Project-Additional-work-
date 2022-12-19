package com.umdaa.nurseasst.ViewModels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.umdaa.nurseasst.Database.CommonDao
import com.umdaa.nurseasst.Database.NurseDatabase
import com.umdaa.nurseasst.Objects.Patient

class PatientViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = this.javaClass.getSimpleName()
    private val commonDao: CommonDao
    private val database: NurseDatabase?
    internal val allPatient: LiveData<List<Patient>>

    init {

        database = NurseDatabase.getDatabase(application)
        commonDao = database!!.commonDao()
        allPatient = commonDao.allPatients

    }

    fun insert(patient: Patient) {
        InsertAsyncTask(commonDao).execute(patient)
    }



    fun update(patient: Patient) {
        UpdateAsyncTask(commonDao).execute(patient)
    }

    fun delete(patient: Patient) {
        DeleteAsyncTask(commonDao).execute(patient)
    }


    private open inner class OperationAsyncTask internal constructor(internal var mAsyncTask: CommonDao) :
        AsyncTask<Patient, Void, Void>() {
        override fun doInBackground(vararg patient: Patient): Void? {
            return null
        }

    }

    private open inner class InsertAsyncTask internal constructor(commonDao: CommonDao) :
        OperationAsyncTask(commonDao) {
        override fun doInBackground(vararg patient: Patient): Void? {
            mAsyncTask.insertPatients(patient[0])
            return null
        }
    }

    private open inner class UpdateAsyncTask internal constructor(commonDao: CommonDao) :
        OperationAsyncTask(commonDao) {

        override fun doInBackground(vararg patient: Patient): Void? {
            mAsyncTask.update(patient[0])
            return null
        }
    }


    private open inner class DeleteAsyncTask internal constructor(commonDao: CommonDao) :
        OperationAsyncTask(commonDao) {

        override fun doInBackground(vararg patient: Patient): Void? {
            mAsyncTask.delete(patient[0])
            return null
        }
    }
}
