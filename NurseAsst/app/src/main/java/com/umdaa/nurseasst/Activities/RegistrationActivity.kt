package com.umdaa.nurseasst.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.umdaa.nurseasst.Network.ServiceBuilder
import com.umdaa.nurseasst.Objects.DefaultResponse
import com.umdaa.nurseasst.R
import kotlinx.android.synthetic.main.activity_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private var animationUp: Animation? = null
    private var animationDown: Animation? = null
    internal var DELAY = 3000
    private var patient_gender: Spinner? = null
    private var gender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val homescreentoolbar = findViewById<View>(R.id.include_registration_toolbar) as Toolbar
        setSupportActionBar(homescreentoolbar)
        homescreentoolbar.setTitle("REGISTER ")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        homescreentoolbar.setNavigationOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        registration_validation_status!!.visibility = View.GONE

        animationUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        animationDown = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)

        patient_gender = findViewById(R.id.add_nurse_genders)
        val genders = resources.getStringArray(R.array.Genders)
        // access the spinner
        if (patient_gender != null) {
            val adapter = ArrayAdapter(this, R.layout.spinner_items, genders)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
            patient_gender!!.adapter = adapter


        }


        btn_registeration_submit.setOnClickListener {
            gender = patient_gender!!.getSelectedItem().toString()
            val name = add_nurse_name.text.toString().trim()
            val gender = gender.toString().trim()
            val email = add_nurse_email.text.toString().trim()
            val phone = add_nurse_number.text.toString().trim()
            val clinic = add_nurse_clinic.text.toString().trim()
            val password = add_nurse_password.text.toString().trim()
            val confirm_password = add_nurse_confirm_password.text.toString().trim()
            val location = add_nurse_location.text.toString().trim()
            val age = add_nurse_age.text.toString().trim()


            if (name.isEmpty() && gender == "Gender" && email.isEmpty() && phone.isEmpty() && clinic.isEmpty() &&
                password.isEmpty() && confirm_password.isEmpty() && location.isEmpty() && age.isEmpty()
            ) {
                check("Please fill all the fields")


            }else if(name.isEmpty()){

                check("Please enter your name")

            } else if(gender == "Gender"){
                check("Please select your gender")
            }else if (age.isEmpty()) {
                check("Please enter your age")
            }else if (email.isEmpty()) {
                check("Please enter your email")
            } else if(phone.isEmpty()) {
                check("Please enter your phone number")
            }else if(password.isEmpty()){
                check("Please enter your password")
            } else if(clinic.isEmpty()) {
                check("Please enter your clinic")

            }else if(location.isEmpty()) {
                check("Please enter your location")
            }else{

                if (password.equals(confirm_password)) {

                    ServiceBuilder.instance.nurseRegistration(
                        name,
                        gender,
                        email,
                        phone,
                        password,
                        clinic,
                        location,
                        age
                    ).enqueue(object : Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, "Registration Not Successful", Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<DefaultResponse>,
                            response: Response<DefaultResponse>
                        ) {


                            add_nurse_password.setBackgroundResource(R.color.grey)
                            add_nurse_confirm_password.setBackgroundResource(R.color.grey)

                            Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_LONG).show()

                            finish()
                        }
                    })


                } else {

                    check("Password did not match")
                    add_nurse_password.setBackgroundResource(R.color.airRed)
                    add_nurse_confirm_password.setBackgroundResource(R.color.airRed)

                }
            }
        }


    }


    fun check(message: String) {

        registration_validation_status!!.visibility = View.VISIBLE
        registration_validation_message.setText(message)
        registration_validation_status!!.startAnimation(animationDown)
        val handler = Handler()
        handler.postDelayed({
            registration_validation_status!!.visibility = View.GONE
            registration_validation_status!!.startAnimation(animationUp)
        }, DELAY.toLong())
    }
}
