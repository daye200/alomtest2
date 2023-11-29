package com.example.alomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.Headers
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface Api{
    @POST("api/v1/email/verification-request")
    fun userLogin(
        @Body jsonParams : UserModel
    ): Call<LoginBackendResponse>


    companion object {
        private const val BASE_URL = "https://localhost"
        val gson : Gson =   GsonBuilder().setLenient().create();

        fun create() : Api{

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }

}