package com.umdaa.nurseasst.Network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.umdaa.nurseasst.Activities.HomeScreenActivity
import com.umdaa.nurseasst.Commons.Constants
import com.umdaa.nurseasst.Database.CommonDao
import com.umdaa.nurseasst.Database.NurseDatabase
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.ViewModels.PatientViewModel
import kotlinx.android.synthetic.main.patient_list_item.view.*

class NetworkMoniter : BroadcastReceiver() {
    var patientViewModel: PatientViewModel? = null
    private var addPatients: List<Patient>? = null
    private var patients: Patient? = null
    private var commonDao: CommonDao? = null
    private lateinit var nurseDatabase: NurseDatabase
    private var cursor: Cursor? = null
    private var data : LiveData<Patient>? = null



    override fun onReceive(context: Context?, intent: Intent?) {

        nurseDatabase = NurseDatabase.getDatabase(context!!)!!


        if(Constants.isConnectingToInternet(context!!)){





                Toast.makeText(context,"Internet connected" + data ,Toast.LENGTH_LONG).show()




        }else{

        }
    }

    fun setList(addPatient: List<Patient>) {
        addPatients = addPatient
    }
}