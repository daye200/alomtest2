package com.example.alomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alomtest.databinding.FinishLayoutBinding
import com.example.alomtest.databinding.FirstLayoutBinding
import com.example.alomtest.databinding.LoginLayoutBinding

class Finish : AppCompatActivity() {
    lateinit var binding: FinishLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.finishStart.setOnClickListener{
            val intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }
    }
}