package com.umdaa.nurseasst.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umdaa.nurseasst.Objects.Vitals
import com.umdaa.nurseasst.R
import com.umdaa.nurseasst.ViewModels.VitalsViewModel

class VitalsAdapter(
    private val mContext: Context,
    private val onDeleteClickListeners: OnDeleteClickListeners
) : RecyclerView.Adapter<VitalsAdapter.VitalsViewHolder>() {

    private var vitals: List<Vitals>? = null

    private val layoutInflater: LayoutInflater
    var vitalsViewModel: VitalsViewModel? = null
//    private var list = ArrayList<Int>() //Creating arraylist
    private var REQUEST: Int? = null


    init {
        layoutInflater = LayoutInflater.from(mContext)
    }

    interface OnDeleteClickListeners {
        fun OnDeleteClickListeners(vitals: Vitals)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VitalsViewHolder {
        val itemView = layoutInflater.inflate(R.layout.vitals_item, parent, false)
        return VitalsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (vitals != null)
            vitals!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: VitalsViewHolder, position: Int) {


        if (vitals != null) {
            var vitals = vitals!![position]
            holder.setData(
                vitals.levelConsciousness!!,
                vitals.pulseRate!!,
                vitals.bp!!,
                vitals.bp1!!,
                vitals.respRate!!,
                vitals.temperature!!,
                vitals.saturation!!,
                vitals.oxygen!!,
                vitals.copd!!,
                vitals.note!!,
                vitals.heart_rate_value!!,
                vitals.blood_pressure_value!!,
                vitals.resp_rate_value!!,
                vitals.temperature_value!!,
                vitals.saturation_value!!,
                vitals.oxy_value!!.toInt(),
                vitals.loc_value!!,
                vitals.priority!!,
                position
            )
            holder.setListeners()
        }
    }


    fun setList(vitalList: List<Vitals>) {
        vitals = vitalList
        notifyDataSetChanged()
    }

    inner class VitalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var hr: Int? = null
        val consciousness: TextView
        val heart_rate: TextView
        val bp: TextView
        val resp_rate: TextView
        val temperature_rate: TextView
        val saturation_rate: TextView
        val oxygen_status: TextView
        val copd_status: TextView
        var colorStatus: TextView
        var priorityStatus: TextView
        var note: TextView
        var heart_rate_value : String? = null
        var blood_pressure_value : String? = null
        var resp_rate_value : String? = null
        var temperature_value : String? = null
        var saturation_value : String? = null
        var oxygen_value : Int? = null
        var loc_value : String? = null
        var priority : String? = null
        private var mPosition: Int = 0
        private val imgDelete: LinearLayout
        private val heartRateolor: LinearLayout
        private val bloodPressureColor: LinearLayout
        private val respRateColor: LinearLayout
        private val temperatureColor: LinearLayout
        private val saturationColor: LinearLayout
        private val vitalsContainer: ConstraintLayout


//        private val connect : ConstraintLayout

        init {
            consciousness = itemView.findViewById(R.id.id_level_of_consciousness)
            colorStatus = itemView.findViewById(R.id.id_time_status)
            heart_rate = itemView.findViewById(R.id.pulse_rate)
            bp = itemView.findViewById(R.id.bp)
            resp_rate = itemView.findViewById(R.id.resp_rate)
            temperature_rate = itemView.findViewById(R.id.temperature)
            saturation_rate = itemView.findViewById(R.id.saturation)
            oxygen_status = itemView.findViewById(R.id.id_oxygen)
            copd_status = itemView.findViewById(R.id.copd)
            priorityStatus = itemView.findViewById(R.id.priroity)
            imgDelete = itemView.findViewById(R.id.delete_patient)
            heartRateolor = itemView.findViewById(R.id.pulsrateColorStatus)
            bloodPressureColor = itemView.findViewById(R.id.bpColorStatsu)
            respRateColor = itemView.findViewById(R.id.resprateColorStatus)
            temperatureColor = itemView.findViewById(R.id.temperatureColorStatus)
            saturationColor = itemView.findViewById(R.id.saturationcolorStatus)
            vitalsContainer = itemView.findViewById(R.id.vitals_container)
            note = itemView.findViewById(R.id.tx_note)
//            connect = itemView.findViewById(R.id.connect)
        }

        fun setData(
            levelOfConsciousness: String,
            heartRate: String,
            bloodPressure: String,
            bloodPressure1: String,
            respRate: String,
            temperature: String,
            saturation: String,
            oxygenMsk: String,
            copd: String,
            notes: String,
            heart_rate_values: String,
            blood_pressure_values: String,
            resp_rate_values: String,
            temperature_values: String,
            saturation_values: String,
            oxygen_values: Int,
            loc_values: String,
            prioritys: String,
            position: Int
        ) {


            consciousness.text = levelOfConsciousness
            heart_rate.text = heartRate
            bp.text = bloodPressure + " / " + bloodPressure1
            resp_rate.text = respRate
            temperature_rate.text = temperature
            saturation_rate.text = saturation
            oxygen_status.text = oxygenMsk
            copd_status.text = copd
            note.text = notes
            heart_rate_value = heart_rate_values
            blood_pressure_value = blood_pressure_values
            resp_rate_value = resp_rate_values
            temperature_value = temperature_values
            saturation_value = saturation_values
            oxygen_value = oxygen_values
            loc_value = loc_values
            priority = prioritys
            mPosition = position

            var ConsciousLevel: Int? = null
            var heartRateColor: Int? = null
            var bpColor: Int? = null
            var respRateColors: Int? = null
            var tempColor: Int? = null
            var saturationColors: Int? = null
            var oxg : Int? = null

            if (loc_value != null) {

                ConsciousLevel = loc_value!!.toInt()

                if (ConsciousLevel == 0) {
                    consciousness.setTextColor(ContextCompat.getColor(mContext, R.color.green))
                }  else if (ConsciousLevel == 3) {
                    consciousness.setTextColor(ContextCompat.getColor(mContext, R.color.red))
                } else if (ConsciousLevel == 101) {
                    consciousness.setText("Not Mentioned")
                }
            }


            if (heart_rate_value != null) {

                heartRateColor = heart_rate_value!!.toInt()

                if (heartRateColor == 0) {
                    heartRateolor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.green
                        )
                    )
                } else if (heartRateColor == 1 || heartRateColor == 2) {
                    heartRateolor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow
                        )
                    )
                } else if (heartRateColor == 3) {
                    heartRateolor.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red))
                }
            }


            if (blood_pressure_value != null) {

                bpColor = blood_pressure_value!!.toInt()

                if (bpColor == 0) {
                    bloodPressureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.green
                        )
                    )
                } else if (bpColor == 1 || bpColor == 2) {
                    bloodPressureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow
                        )
                    )
                } else if (bpColor == 3) {
                    bloodPressureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.red
                        )
                    )
                }
            }

            if (resp_rate_value != null) {

                respRateColors = resp_rate_value!!.toInt()

                if (respRateColors == 0) {
                    respRateColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.green
                        )
                    )
                } else if (respRateColors == 1 || respRateColors == 2) {
                    respRateColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow
                        )
                    )
                } else if (respRateColors == 3) {
                    respRateColor.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red))
                }
            }


            if (temperature_value != null) {

                tempColor = temperature_value!!.toInt()

                if (tempColor == 0) {
                    temperatureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.green
                        )
                    )
                } else if (tempColor == 1 || tempColor == 2) {
                    temperatureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow
                        )
                    )
                } else if (tempColor == 3) {
                    temperatureColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.red
                        )
                    )
                }

            }


            if (saturation_value != null) {
                saturationColors = saturation_value!!.toInt()

                if (saturationColors == 0) {
                    saturationColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.green
                        )
                    )
                } else if (saturationColors == 1 || saturationColors == 2) {
                    saturationColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.yellow
                        )
                    )
                } else if (saturationColors == 3) {
                    saturationColor.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.red
                        )
                    )
                }
            }

            if(oxygen_value != null){

                oxg = oxygen_value!!.toInt()
                if (oxg == 0){
                    oxygen_status.setText("NO")
                    oxygen_status.setTextColor(ContextCompat.getColor(mContext, R.color.green))

                }else if(oxg == 1){
                    oxygen_status.setText("YES")
                    oxygen_status.setTextColor(ContextCompat.getColor(mContext, R.color.red))

                }else if(oxg == 101){
                    oxygen_status.setText("NONE")
                }
            }



            val list = arrayListOf(
                ConsciousLevel!!,
                heartRateColor!!,
                bpColor!!,
                respRateColors!!,
                tempColor!!,
                saturationColors!!,
                oxg!!
            )

            checkForMediumRiskValue(list!!)
            val set_priority = priority!!.toInt()

            if (set_priority == 0) {
                priorityStatus.setText("LOW RISK")
                priorityStatus.setTextColor(ContextCompat.getColor(mContext, R.color.green))
                vitalsContainer.setBackgroundResource(R.drawable.shadow_green)
            } else if (set_priority == 1 ) {
                priorityStatus.setText("MEDIUM RISK")
                priorityStatus.setTextColor(ContextCompat.getColor(mContext, R.color.yellow))
                vitalsContainer.setBackgroundResource(R.drawable.shadow_yellow)
            } else if (set_priority == 2) {
                priorityStatus.setText("HIGH RISK")
                priorityStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red))
                vitalsContainer.setBackgroundResource(R.drawable.showdow_red)
            }


        }

        fun setListeners() {
//            connect.setOnClickListener {
//                val intent = Intent(mContext, VitalsListActivity::class.java)
//                intent.putExtra("patient_id", addPatients!![mPosition].id)
//                intent.putExtra("patient_name", addPatients!![mPosition].name)
//                (mContext as Activity).startActivity(intent)
//                mContext.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }

            imgDelete.setOnClickListener {
                //                onDeleteClickListeners?.OnDeleteClickListeners(vitals!![mPosition])
                onDeleteClickListeners.OnDeleteClickListeners(vitals!![mPosition])
            }
        }


    }


    fun checkForMediumRiskValue(list : ArrayList<Int>) {

        for (i in 0..list.size - 1) {

            if (list[i] == 3) {
                REQUEST = 100
            }
        }
    }
}