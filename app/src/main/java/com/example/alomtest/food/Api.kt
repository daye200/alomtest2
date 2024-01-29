package com.example.alomtest.food

import com.example.alomtest.retrofit.LoginBackendResponse
import com.example.alomtest.retrofit.LoginBackendResponse10
import com.example.alomtest.retrofit.LoginBackendResponse11
import com.example.alomtest.retrofit.LoginBackendResponse12
import com.example.alomtest.retrofit.LoginBackendResponse13
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.example.alomtest.retrofit.LoginBackendResponse6
import com.example.alomtest.retrofit.LoginBackendResponse7
import com.example.alomtest.retrofit.LoginBackendResponse8
import com.example.alomtest.retrofit.LoginBackendResponse9
import com.example.alomtest.retrofit.exercise_list
import com.example.alomtest.retrofit.food_list
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api{


    @POST("api/user/login")
    fun userLogin(
//        @Query("user_id") username: String,
//        @Query("password") password: String

        @Body jsonParams: JsonElement
    ): Call<LoginBackendResponse>


    @POST("api/email/verification-request")
    fun send_authetication_code(
        @Body jsonParams : JsonElement
    ): Call<LoginBackendResponse2>



    @POST("api/email/verification")
    fun check_email(
        @Body jsonParams : JsonElement
    ): Call<LoginBackendResponse3>

//    @POST("api/user/body-information")
//    fun search_bodyinfo(
//        @Body jsonParams : JsonElement
//    ): Call<LoginBackendResponse4>


//    @POST("api/user/save-body-information")
//    fun newuserinput(
//        @Body jsonParams: JsonElement
//    ):Call<LoginBackendResponse5>

    @POST("api/user/signup")
    fun signup(
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse6>
    //fun fetchPosts(@Header("Authorization") token: String): Call<PostsResponse>
    @POST("api/user/body-information")
    fun loadData(
        @Header("Authorization") accessToken:String,
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse7>


    @POST("api/user/change-password")
    fun change_password(
        @Header("Authorization") accessToken:String,
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse8>
    @POST("api/email/password-change-verification-request")
    fun send_email_forget_password(
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse9>

    @POST("api/email/password-change-verification")
    fun check_email_forget_password(
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse10>

    @POST("api/user/change-forget-password")
    fun change_forget_password(
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse11>

    @POST("api/user/save-body-information")
    fun change_bodyinfo(
        @Header("Authorization") accessToken:String,
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse12>


    @POST("api/refresh")
    fun refreshToken(
        @Header("Authorization") accessToken:String,
        @Body jsonParams: JsonElement
    ):Call<LoginBackendResponse13>

    @GET("api/exercise")
    fun load_exercise(
        @Header("Authorization") accessToken:String,
    ):Call<ArrayList<exercise_list>>
//    @POST("api/user/change-forget-password")
//    fun reset_password(
//        @Body jsonParams: JsonElement
//    ):Call<LoginBackendResponse9>

    @GET("api/food")
    fun load_food(
        //@Header("Authorization") accessToken:String,
    ):Call<ArrayList<food_list>>


    companion object {
        private const val BASE_URL = "http://3.34.218.4:8080"
        val gson : Gson =   GsonBuilder().setLenient().create();

        fun create() : Api {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }

}