package com.umdaa.nurseasst.Database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.Objects.Vitals

@Dao
interface CommonDao {

    @get:Query("SELECT * FROM Patients")
    val allPatients: LiveData<List<Patient>>

    @Query("SELECT * FROM Patients WHERE sync_status==0")
    fun getPatientSync(): LiveData<Patient>


    @Insert
    fun insert(addPatient: Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatients(addPatient: Patient)

    @Query("SELECT * FROM Patients WHERE id=:patient_Id")
    fun getPatient(patient_Id: String): LiveData<Patient>

    @Update
    fun update(addPatient:Patient)

    @Delete
    fun delete(addPatient: Patient): Int




    @get:Query("SELECT * FROM VitalsList")
    val allPatientVitals: LiveData<List<Vitals>>


    @Ignore
    @Insert
    fun insertVitals(vitals: Vitals)

    @Update
    fun update(vitals: Vitals)

    @Delete
    fun delete(vitals: Vitals): Int

    @Query("SELECT * FROM VitalsList WHERE patient_id=:patient_Id")
    fun getVitals(patient_Id: Int): LiveData<List<Vitals>>

}