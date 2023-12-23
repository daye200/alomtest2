package com.example.alomtest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

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