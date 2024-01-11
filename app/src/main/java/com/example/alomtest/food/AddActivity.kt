package com.example.alomtest.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.alomtest.R

class AddActivity : AppCompatActivity() {
    companion object {
        const val RESULT_ADD_TASK = 123 // Any unique value
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        findViewById<Button>(R.id.add_next).setOnClickListener {
            val newTask = findViewById<EditText>(R.id.add_edit).text.toString()

            val resultintent = Intent(this, food::class.java)
            resultintent.putExtra("newTask", newTask)
            setResult(RESULT_ADD_TASK, resultintent)
            finish()
    }
}}