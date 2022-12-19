package com.umdaa.nurseasst.Network

import com.umdaa.nurseasst.Objects.DefaultResponse
import com.umdaa.nurseasst.Objects.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {


    @FormUrlEncoded
    @POST("nurseRegistration")
    fun nurseRegistration(

        @Field("nurse_name") name:String,
        @Field("nurse_gender") gender:String,
        @Field("nurse_email") email:String,
        @Field("nurse_phone_number") phone:String,
        @Field("nurse_password") password:String,
        @Field("nurse_clinic_name") clinic:String,
        @Field("nurse_location") location:String,
        @Field("nurse_age") age:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("nurseLogin")
    fun nurseLogin(

        @Field("nurse_email") email: String,
        @Field("nurse_password") password: String

    ): Call<LoginResponse>



    @FormUrlEncoded
    @POST("nursePatientRegistration")
    fun patientRegistration(

        @Field("patient_name") name:String,
        @Field("patient_gender") gender:String,
        @Field("patient_id_number") aadharId:String,
        @Field("patient_mobile_number") phone:String,
        @Field("patient_location") location:String,
        @Field("patient_age") age:String,
        @Field("nurse_id") nurseId:String
    ): Call<DefaultResponse>


}