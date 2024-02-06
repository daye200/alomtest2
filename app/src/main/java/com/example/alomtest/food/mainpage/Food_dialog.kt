package com.example.alomtest.food.mainpage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.example.alomtest.R

class Food_dialog(context: Context, private val dataTitle: String) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)
        dialogTitle.text = dataTitle
    }
}