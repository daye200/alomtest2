package com.example.alomtest

import android.content.Intent
import android.os.Bundle
import retrofit2.Call
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alomtest.databinding.LoginLayoutBinding
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var binding: LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginforget.setOnClickListener {
            val intent = Intent(this,FindpasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginbutton.setOnClickListener {
            val id = binding.loginemail.text.toString().trim()//trim : 문자열 공백제거
            val pw = binding.loginpassword.text.toString().trim()

            saveData(id, pw)//db (shared preference)에 데이터 저장 (자동 로그인 용)

            // == 백엔드 통신 부분 ==
            val api = Api.create()//
            val data = UserModel(id, pw)


            api.userLogin(data).enqueue(object : Callback<LoginBackendResponse> {
                override fun onResponse(
                    call: Call<LoginBackendResponse>,
                    response: Response<LoginBackendResponse>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())

                    when (response.code()) {
                        200 -> {
                            saveData(id, pw)
                        }
                        404 -> Toast.makeText(this@login, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@login, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                    // 실패
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })
        }
    }fun saveData( id : String, pw : String){
        val prefID = this.getSharedPreferences("userID", MODE_PRIVATE)
        val prefPW = this.getSharedPreferences("userPW", MODE_PRIVATE)
        val editID = prefID.edit()
        val editPW = prefPW.edit()
        editID.putString("id", id)
        editPW.putString("pw", pw)
        editID.apply()//save
        editPW.apply()//save
        Log.d("로그인 데이터", "saved")
    }
}
