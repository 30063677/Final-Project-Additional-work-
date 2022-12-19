package com.umdaa.nurseasst.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.umdaa.nurseasst.Adapter.VitalsAdapter
import com.umdaa.nurseasst.R
import com.umdaa.nurseasst.ViewModels.VitalsViewModel
import kotlinx.android.synthetic.main.activity_vitals.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.umdaa.nurseasst.Objects.Vitals





class VitalsActivity : AppCompatActivity(), VitalsAdapter.OnDeleteClickListeners {


    private val TAG = this.javaClass.getSimpleName()
    var vitalsViewModel: VitalsViewModel? = null
    private var vitalsAdapter: VitalsAdapter? = null
    private var intValue : Int? = null
    private var patientName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)
        val homescreentoolbar = findViewById<View>(R.id.vitals_screen_toolbar) as Toolbar
        setSupportActionBar(homescreentoolbar)
        val mIntent = intent
        val Intent = intent
         intValue = mIntent.getIntExtra("patient_id", 0)
         patientName = Intent.getStringExtra("patient_name")
        homescreentoolbar.setTitle(patientName)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        homescreentoolbar.setNavigationOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        add_patient_vitals.setOnClickListener {
            val intent = Intent(this, AddVitalsActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        initRecyclerVIew()
        vitalsViewModel = VitalsViewModel(application, intValue!!)
        vitalsViewModel!!.allPatientVitals.observe(this,
            androidx.lifecycle.Observer { addPatient -> vitalsAdapter!!.setList(addPatient) })
    }

    private fun initRecyclerVIew() {
        vitals_recycler_view.apply {


            layoutManager = LinearLayoutManager(context)
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            vitalsAdapter = VitalsAdapter(context, this@VitalsActivity)
            adapter = vitalsAdapter

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val patient = Vitals(intValue!!,
                data!!.getStringExtra(AddVitalsActivity.CONCIOUSNESS),
                data!!.getStringExtra(AddVitalsActivity.HEART_RATE),
                data!!.getStringExtra(AddVitalsActivity.BLOOD_PRESSURE),
                data!!.getStringExtra(AddVitalsActivity.BLOOD_PRESSURE1),
                data!!.getStringExtra(AddVitalsActivity.RESP_RATE),
                data!!.getStringExtra(AddVitalsActivity.TEMPERATURE),
                data!!.getStringExtra(AddVitalsActivity.SATURATION),
                data!!.getStringExtra(AddVitalsActivity.OXYGEN),
                data!!.getStringExtra(AddVitalsActivity.COPD),
                data!!.getStringExtra(AddVitalsActivity.NOTE),
                data!!.getStringExtra(AddVitalsActivity.HEART_RATE_VALUE),
                data!!.getStringExtra(AddVitalsActivity.BLOOD_PRESSURE_VALUE),
                data!!.getStringExtra(AddVitalsActivity.RESP_RATE_VALUE),
                data!!.getStringExtra(AddVitalsActivity.TEMPERATURE_VALUE),
                data!!.getStringExtra(AddVitalsActivity.SATURATION_VALUE),
                data!!.getIntExtra(AddVitalsActivity.OXYGEN_VALUE,0),
                data!!.getStringExtra(AddVitalsActivity.LEVELOFCONSCIOUSNESS_VALUE),
                data!!.getStringExtra(AddVitalsActivity.PRIORITY)
            )
            vitalsViewModel!!.insert(patient)

         //   Toast.makeText(applicationContext, "Added in database", Toast.LENGTH_LONG).show()
        }
    }

    override fun OnDeleteClickListeners(vitals: Vitals) {
        vitalsViewModel!!.delete(vitals)
    }

    companion object {

         val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
         val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}
