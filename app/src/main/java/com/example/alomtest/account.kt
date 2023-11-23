package com.example.alomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alomtest.databinding.AccountLayoutBinding
import com.example.alomtest.databinding.FirstLayoutBinding

class account : AppCompatActivity() {
    lateinit var binding: AccountLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}