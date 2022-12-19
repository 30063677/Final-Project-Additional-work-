package com.umdaa.nurseasst.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.umdaa.nurseasst.Activities.VitalsActivity
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.R
import androidx.core.view.ViewCompat
import androidx.core.app.ActivityOptionsCompat
import android.app.ActivityOptions
import android.os.Build
import androidx.annotation.RequiresApi


class PatientListAdapter(
    private val mContext: Context,
    private val onDeleteClickListener: OnDeleteClickListener
)
    : RecyclerView.Adapter<PatientListAdapter.ViewHolder>() {


    private val layoutInflater: LayoutInflater
    private var addPatients: List<Patient>? = null

    init {
        layoutInflater = LayoutInflater.from(mContext)
    }

    interface OnDeleteClickListener {
        fun OnDeleteClickListener(myPatients: Patient)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientListAdapter.ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.patient_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (addPatients != null)
            addPatients!!.size
        else
            0
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: PatientListAdapter.ViewHolder, position: Int) {
        if (addPatients != null) {
            val patient = addPatients!![position]
            holder.setData(
                patient.name,
                patient.age + " Y,",
                patient.gender,
                patient.location,
                position
            )
            holder.setListeners()
        }
    }

    fun setList(addPatient: List<Patient>) {
        addPatients = addPatient
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val patientName: TextView
        val patientAge: TextView
        val patientLocation: TextView
        val patientGenderIcon: ImageView

        private var mPosition: Int = 0
        private val imgDelete: ImageView
        private val connect : ConstraintLayout

        init {
            patientName = itemView.findViewById(R.id.patient_name)
            patientAge = itemView.findViewById(R.id.patient_age)
            patientLocation = itemView.findViewById(R.id.patient_location)
            imgDelete = itemView.findViewById(R.id.delete_patient)
            patientGenderIcon = itemView.findViewById(R.id.patientGenderIcon)
            connect = itemView.findViewById(R.id.connect)
        }

        fun setData(name: String, age : String, gender: String, location : String, position: Int) {
            patientName.text = name
            patientAge.text = age
             patientLocation.text = location
            if(gender == "Male"){
                patientGenderIcon.setImageResource(R.drawable.ic_male_icon)
            } else if(gender == "Female"){
                patientGenderIcon.setImageResource(R.drawable.ic_female_icon)
            }
            mPosition = position
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun setListeners() {
            connect.setOnClickListener {
                val intent = Intent(mContext, VitalsActivity::class.java)
                intent.putExtra("patient_id", addPatients!![mPosition].id)
                intent.putExtra("patient_name", addPatients!![mPosition].name)
                val options = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity,connect,
                    ViewCompat.getTransitionName(connect))

               (mContext as Activity).startActivity(intent)
                mContext.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

            imgDelete.setOnClickListener {
                onDeleteClickListener?.OnDeleteClickListener(addPatients!![mPosition])
            }
        }
    }
}