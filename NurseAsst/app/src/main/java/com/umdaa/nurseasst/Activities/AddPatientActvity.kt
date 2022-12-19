package com.umdaa.nurseasst.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.umdaa.nurseasst.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_add_patient_actvity.*
import kotlinx.android.synthetic.main.activity_registration.*

class AddPatientActvity : AppCompatActivity() {

    private var patient_image: CircleImageView? = null
    private var patient_name: EditText? = null
    private var patient_age: EditText? = null
    private var patient_number: EditText? = null
    private var patient_id: EditText? = null
    private var patient_location: EditText? = null
    private var patient_fees: EditText? = null
    private var patient_gender: Spinner? = null
    private var gender: String? = null


    private var animationUp: Animation? = null
    private var animationDown: Animation? = null
    internal var DELAY = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient_actvity)
        var toolbar = findViewById(R.id.include) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        toolbar.setTitle("Add Patient")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        patient_image = findViewById(R.id.add_patient_image)
        patient_name = findViewById(R.id.add_patient_name)
        patient_age = findViewById(R.id.add_patient_age)
        patient_number = findViewById(R.id.add_patient_number)
        patient_id = findViewById(R.id.add_patient_id)
        patient_location = findViewById(R.id.add_patient_location)
        patient_gender = findViewById(R.id.add_patient_gender)

        val genders = resources.getStringArray(R.array.Genders)

        // access the spinner
        if (patient_gender != null) {
            val adapter = ArrayAdapter(this, R.layout.spinner_items, genders)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            patient_gender!!.adapter = adapter

            gender = patient_gender!!.getSelectedItem().toString()

        }

        add_patient_validation_status!!.visibility = View.GONE

        animationUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        animationDown = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)


        id_submit.setOnClickListener {

            val resultIntent = Intent()

            if (patient_name!!.text.isEmpty()) {

                setResult(Activity.RESULT_CANCELED, resultIntent)

                check("Please enter all fields")

            } else {


                val patient_name = patient_name!!.text.toString()
                val Text = patient_gender!!.getSelectedItem().toString()
                val patient_gender = Text
                val patient_age = patient_age!!.text.toString()
                val patient_number = patient_number!!.text.toString()
                val patient_id = patient_id!!.text.toString()
                val patient_location = patient_location!!.text.toString()


                if (patient_name.isEmpty()) {
                    check("Plese enter patient name")
                } else if (patient_gender == "Gender") {
                    check("Plese select patient gender")
                } else if (patient_age.isEmpty()) {
                    check("Plese enter patient age")
                } else if (patient_number.isEmpty()) {
                    check("Plese enter patient phone number")
                } else if (patient_id.isEmpty()) {
                    check("Plese enter patient Id")
                }else if (patient_location.isEmpty()) {
                        check("Plese enter patient location")
                } else {


                    resultIntent.putExtra(PATIENT_NAME, patient_name)
                    resultIntent.putExtra(PATIENT_GENDER, patient_gender)
                    resultIntent.putExtra(PATIENT_AGE, patient_age)
                    resultIntent.putExtra(PATIENT_NUMBER, patient_number)
                    resultIntent.putExtra(PATIENT_ID, patient_id)
                    resultIntent.putExtra(PATIENT_LOCATION, patient_location)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }

                //Toast.makeText(applicationContext, " Added in list", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun check(message: String) {

        add_patient_validation_status!!.visibility = View.VISIBLE
        add_patient_validation_message.setText(message)
        add_patient_validation_status!!.startAnimation(animationDown)
        val handler = Handler()
        handler.postDelayed({
            add_patient_validation_status!!.visibility = View.GONE
            add_patient_validation_status!!.startAnimation(animationUp)
        }, DELAY.toLong())
    }

    companion object {

        val REQUEST_CODE = 200
        val PATIENT_NAME = "patient_name"
        val PATIENT_AGE = "patient_age"
        val PATIENT_GENDER = "patient_gender"
        val PATIENT_NUMBER = "patient_number"
        val PATIENT_ID = "patient_aadhar"
        val PATIENT_LOCATION = "patient_location"
    }
}
