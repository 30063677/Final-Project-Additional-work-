package com.umdaa.nurseasst.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.umdaa.nurseasst.Adapter.PatientListAdapter
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.R
import com.umdaa.nurseasst.ViewModels.PatientViewModel
import kotlinx.android.synthetic.main.activity_home_screen.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.umdaa.nurseasst.Commons.Constants
import com.umdaa.nurseasst.Network.NetworkMoniter
import com.umdaa.nurseasst.SharedPref.SharedPrefManager
import java.util.*


class HomeScreenActivity : AppCompatActivity(), PatientListAdapter.OnDeleteClickListener {

    private val TAG = this.javaClass.getSimpleName()
    var patientViewModel: PatientViewModel? = null
    private var patientListAdapter: PatientListAdapter? = null
    private var networkMoniter: NetworkMoniter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val homescreentoolbar = findViewById<View>(R.id.home_screen_toolbar) as Toolbar
        setSupportActionBar(homescreentoolbar)
        homescreentoolbar.setTitle("HOME ")






        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)
        Log.v(TAG, " owner Oncreate")
        lifecycle.addObserver(HomeScreenObserver())

        add_patient_details.setOnClickListener {
            val intent = Intent(this, AddPatientActvity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }

        initRecyclerVIew()

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel::class.java)
        patientViewModel!!.allPatient.observe(this, androidx.lifecycle.Observer { addPatient -> patientListAdapter!!.setList(addPatient) })
//        patientViewModel!!.allPatient.observe(this, androidx.lifecycle.Observer { addPatient -> networkMoniter!!.setList(addPatient) })


    }


    private fun initRecyclerVIew() {
        patientList.apply {
            layoutManager = LinearLayoutManager(context)
            patientListAdapter = PatientListAdapter(context, this@HomeScreenActivity)
            adapter = patientListAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (Constants.isConnectingToInternet(this)) {

                val sync_status = "false"
                val r = Random()
                val patient_id = r.nextInt(80 - 65) + 65
                val patient = Patient(
                    patient_id,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_NAME)!!,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_GENDER)!!,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_AGE)!!,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_NUMBER)!!,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_ID)!!,
                    data!!.getStringExtra(AddPatientActvity.PATIENT_LOCATION)!!,
                    data!!.getBooleanExtra(sync_status, false)
                )
                patientViewModel?.insert(patient)

                Toast.makeText(applicationContext, "Added in database", Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Observer Removed")
        lifecycle.removeObserver(HomeScreenObserver())
    }

    override fun OnDeleteClickListener(myPatients: Patient) {
        patientViewModel!!.delete(myPatients)
    }


    companion object {
        private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}
