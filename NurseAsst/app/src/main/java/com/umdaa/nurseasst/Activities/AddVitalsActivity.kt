package com.umdaa.nurseasst.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.umdaa.nurseasst.R
import kotlinx.android.synthetic.main.activity_add_vitals.*
import android.os.Handler
import android.widget.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.umdaa.nurseasst.ViewModels.AddVitalsViewModel
import com.umdaa.nurseasst.ViewModels.ValidationViewModel


class AddVitalsActivity : AppCompatActivity() {


    var validation_box: CardView? = null
    private var animationUp: Animation? = null
    private var animationDown: Animation? = null
    var tvs: TextView? = null
    var m_handler: Handler? = null
    var m_handlerTask: Runnable? = null
    var times = 0
    internal var DELAY = 3000
    var oxy : String? = null

    var addVitalsViewModel: AddVitalsViewModel? = null

    var validationViewModel: ValidationViewModel? = null


    private var heart_rate: EditText? = null
    private var blood_pressure: EditText? = null
    private var blood_pressure1: EditText? = null
    private var resp_rate: EditText? = null
    private var ed_temperature: EditText? = null
    private var ed_saturation: EditText? = null
    private var ed_note: EditText? = null
    var conciousness: String? = null
    var locsTxt: String? = null
    var oxygen1: String? = null
    var copd1: String? = null
    var radio_level_of_conciousness: RadioGroup? = null
    var oxygen_status: RadioGroup? = null
    var copd_status: RadioGroup? = null
    var alert: RadioButton? = null
    var verbal: RadioButton? = null
    var pain: RadioButton? = null
    var unconsciousness: RadioButton? = null
    var oxygen_yes: RadioButton? = null
    var oxygen_no: RadioButton? = null
    var copd_yes: RadioButton? = null
    var copd_no: RadioButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vitals)
        val homescreentoolbar = findViewById<View>(R.id.add_vitals_toolbar) as Toolbar
        setSupportActionBar(homescreentoolbar)
        homescreentoolbar.setTitle("ADD VITALS")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        homescreentoolbar.setNavigationOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        validation_box = findViewById(R.id.validation_status);
        tvs = findViewById(R.id.message);
        validation_box!!.visibility = View.GONE

        animationUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        animationDown = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)



        heart_rate = findViewById(R.id.add_pulse_rate)
        blood_pressure = findViewById(R.id.add_blood_pressure)
        blood_pressure1 = findViewById(R.id.add_blood_pressure1)
        resp_rate = findViewById(R.id.add_resp_rate)
        ed_temperature = findViewById(R.id.add_temperature)
        ed_saturation = findViewById(R.id.add_saturation)

        ed_note = findViewById(R.id.id_note)
        alert = findViewById(R.id.id_alert)
        verbal = findViewById(R.id.id_verbal)
        pain = findViewById(R.id.id_pain)
        unconsciousness = findViewById(R.id.id_unconciousness)
        radio_level_of_conciousness = findViewById(R.id.id_radio_level_of_conciousness)
        oxygen_status = findViewById(R.id.id_oxygen_status)
        copd_status = findViewById(R.id.id_status_copd)
        oxygen_yes = findViewById(R.id.oxygen_yes)
        oxygen_no = findViewById(R.id.oxygen_no)
        copd_yes = findViewById(R.id.o2_status_yes)
        copd_no = findViewById(R.id.o2_status_no)



        submit_vitals.setOnClickListener {

            val resultIntent = Intent()

            if (heart_rate!!.text.isEmpty() || blood_pressure!!.text.isEmpty()) {

                setResult(Activity.RESULT_CANCELED, resultIntent)

                check("Please enter all fields")


            } else {

                val consciousness_id = radio_level_of_conciousness!!.checkedRadioButtonId


                if (consciousness_id == alert!!.id) {
                    locsTxt = "ALERT"
                    conciousness = "0"
                } else if (consciousness_id == verbal!!.id) {
                    locsTxt = "VERBAL"
                    conciousness = "3"
                } else if (consciousness_id == pain!!.id) {
                    locsTxt = "PAIN"
                    conciousness = "3"
                } else if (consciousness_id == unconsciousness!!.id) {
                    locsTxt = "UNCONSCIOUSNESS"
                    conciousness = "3"
                } else if (consciousness_id == -1) {
                    locsTxt = "NOT MENTIONED"
                    conciousness = "101"
                }


                val oxygen = oxygen_status!!.checkedRadioButtonId

                if (oxygen == oxygen_yes!!.id) {
                    oxygen1 = "1"
                } else if (oxygen == oxygen_no!!.id) {
                    oxygen1 = "0"
                } else if (oxygen == -1) {
                    oxygen1 = "101"
                }

                val copd = copd_status!!.checkedRadioButtonId

                if (copd == copd_yes!!.id) {
                    copd1 = "YES"
                } else if (copd == copd_no!!.id) {
                    copd1 = "NO"
                } else if (copd == -1) {
                    copd1 = "NONE"
                }


                val heartRate = heart_rate!!.text.toString().trim()
                val bloodPressure = blood_pressure!!.text.toString()
                val bloodPressure1 = blood_pressure1!!.text.toString()
                val respRate = resp_rate!!.text.toString()
                val temperature = ed_temperature!!.text.toString()
                val saturation = ed_saturation!!.text.toString()
                val note = ed_note!!.text.toString()
                val o2 = oxygen1!!.toString()
                val loc = conciousness!!.toString()


                validationViewModel = ValidationViewModel(
                    application,
                    heartRate,
                    bloodPressure,
                    respRate,
                    temperature,
                    saturation
                )



                val hr = validationViewModel!!.heart_rates()
                val bp = validationViewModel!!.bloodPressures()
                val rr = validationViewModel!!.respRates()
                val temp = validationViewModel!!.temperaureCalucation()
                val sat = validationViewModel!!.saturationss()


                if (hr != "null") {

 //                   heart_rate!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.airRed))

                    check(hr)

                } else if (bp != "null") {
 //                   blood_pressure!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.airRed))

                    check(bp)
                } else if (rr != "null") {
 //                   resp_rate!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.airRed))

                    check(rr)
                } else if (temp != "null") {
 //                   ed_temperature!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.airRed))
                    check(temp)
                } else if (sat != "null") {
  //                  ed_saturation!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.airRed))
                    check(sat)
                } else {

                    addVitalsViewModel = AddVitalsViewModel(
                        application,
                        heartRate.toInt(),
                        bloodPressure.toInt(),
                        respRate.toInt(),
                        temperature.toFloat(),
                        saturation.toInt(),
                        o2.toInt(),
                        loc.toInt()

                    )

//                    heart_rate!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))
//                    blood_pressure!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))
//                    resp_rate!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))
//                    ed_temperature!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))
//                    ed_saturation!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.grey))

                    val heart = addVitalsViewModel!!.heartRateCal().toString()
                    val blood = addVitalsViewModel!!.bloodPressureCal().toString()
                    val resp = addVitalsViewModel!!.respRateCal().toString()
                    val temper = addVitalsViewModel!!.temperaureCal().toString()
                    val satu = addVitalsViewModel!!.saturationsCal().toString()
                    val locs = addVitalsViewModel!!.levelOfConsciousnessCal().toString()
                    val prioriry = addVitalsViewModel!!.finalScore().toString()
                    val oxy = addVitalsViewModel!!.oxygenMaskCal()
                    val ts = addVitalsViewModel!!.total_score

                       Toast.makeText(applicationContext, " Score "+ ts, Toast.LENGTH_LONG).show()




                    resultIntent.putExtra(CONCIOUSNESS, locsTxt)
                    resultIntent.putExtra(HEART_RATE, heartRate)
                    resultIntent.putExtra(BLOOD_PRESSURE, bloodPressure)
                    resultIntent.putExtra(BLOOD_PRESSURE1, bloodPressure1)
                    resultIntent.putExtra(RESP_RATE, respRate)
                    resultIntent.putExtra(TEMPERATURE, temperature)
                    resultIntent.putExtra(SATURATION, saturation)
                    resultIntent.putExtra(OXYGEN, oxygen1)
                    resultIntent.putExtra(COPD, copd1)
                    resultIntent.putExtra(NOTE, note)
                    resultIntent.putExtra(HEART_RATE_VALUE, heart)
                    resultIntent.putExtra(BLOOD_PRESSURE_VALUE, blood)
                    resultIntent.putExtra(RESP_RATE_VALUE, resp)
                    resultIntent.putExtra(TEMPERATURE_VALUE, temper)
                    resultIntent.putExtra(SATURATION_VALUE, satu)
                    resultIntent.putExtra(OXYGEN_VALUE, oxy)
                    resultIntent.putExtra(LEVELOFCONSCIOUSNESS_VALUE, locs)
                    resultIntent.putExtra(PRIORITY, prioriry)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }


    fun check(message: String) {

        validation_status!!.visibility = View.VISIBLE
        validation_message.setText(message + "!!")
        validation_status!!.startAnimation(animationDown)
        val handler = Handler()
        handler.postDelayed({
            validation_status!!.visibility = View.GONE
            validation_status!!.startAnimation(animationUp)
        }, DELAY.toLong())
    }


    companion object {

        val CONCIOUSNESS = "conciousness"
        val LOC = "loc"
        val HEART_RATE = "heart_rate"
        val BLOOD_PRESSURE = "blood_pressure"
        val BLOOD_PRESSURE1 = "blood_pressure1"
        val RESP_RATE = "resp_rate"
        val TEMPERATURE = "temperature"
        val SATURATION = "saturation"
        val OXYGEN = "oxygen"
        val COPD = "copd"
        val NOTE = "note"
        val HEART_RATE_VALUE = "heart_rate_value"
        val BLOOD_PRESSURE_VALUE = "blood_pressure_value"
        val RESP_RATE_VALUE = "resp_rate_value"
        val TEMPERATURE_VALUE = "temperature_value"
        val SATURATION_VALUE = "saturation_value"
        val OXYGEN_VALUE = "oxy_value"
        val LEVELOFCONSCIOUSNESS_VALUE = "loc_value"
        val PRIORITY = "priority"

    }
}
