package com.example.alomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alomtest.databinding.FirstLayoutBinding
import com.example.alomtest.databinding.LoginLayoutBinding

class login : AppCompatActivity() {
    lateinit var binding: LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}