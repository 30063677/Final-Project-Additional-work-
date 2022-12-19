package com.umdaa.nurseasst.Network

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
object ServiceBuilder {


    @RequiresApi(Build.VERSION_CODES.O)
    private val AUTH =  Credentials.basic("rest", "university@admin");
    private const val BASE_URL = "http://13.126.121/dev/MobileApi/NurseAsst/"


    @RequiresApi(Build.VERSION_CODES.O)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()


    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }


    class BasicAuthInterceptor(user: String, password: String) : Interceptor {

        private val credentials: String

        init {
            this.credentials = Credentials.basic(user, password)
        }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build()
            return chain.proceed(authenticatedRequest)
        }

    }
}
