package com.example.alomtest.food

import android.widget.SearchView
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.ActivityAddBinding
import com.example.alomtest.food.AddActivity.Companion.RESULT_ADD_TASK
import com.example.sharedpreference.SwipeGesture

import com.google.android.material.card.MaterialCardView
import java.util.Locale

class AddActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var cardView : MaterialCardView
    private lateinit var imageView1 : ImageView
    private lateinit var expandableLayout: View
    private lateinit var expandableLayout2 : View
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    private lateinit var binding: ActivityAddBinding
    private lateinit var expandBtn: Button
    private var isImage1Visible = true

    companion object {
        const val RESULT_ADD_TASK = 123 // Any unique value
    }
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.KITKAT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_add)

        cardView = binding.materialCardView
        recyclerView = binding.recyclerView
        searchView = binding.searchView
        expandableLayout = binding.expandableLayout
        expandableLayout2 = binding.expandableLayout2
        expandBtn = binding.expandBtn
        imageView1 = binding.foodimageView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter

        addDataToList()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

   findViewById<Button>(R.id.add_next).setOnClickListener{
//        binding.addNext.setOnClickListener {
            val newTask = findViewById<EditText>(R.id.add_edit).text.toString()

            val resultintent = Intent(this, food::class.java)
            resultintent.putExtra("newTask", newTask)
            setResult(RESULT_ADD_TASK, resultintent)
            finish()
        }

//       binding.expandBtn.setOnClickListener {
   findViewById<Button>(R.id.expandBtn).setOnClickListener {
       Log.d("toggle", "expandBtn 클릭됨")
       Log.d("ImageView", "imageView1 is null: ${imageView1 == null}")
       runOnUiThread {
            toggleImage()

            if (expandableLayout.visibility == View.GONE) {
                expandableLayout.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
                cardView.visibility = View.GONE
            }
        }}

//  binding.expandBtn2.setOnClickListener {
        findViewById<Button>(R.id.expandBtn2).setOnClickListener {
            if (expandableLayout2.visibility == View.GONE) {
                expandableLayout2.visibility = View.VISIBLE
                cardView.visibility = View.VISIBLE
            } else {
                expandableLayout2.visibility = View.GONE
            }
        }

        deleteData()


    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<FoodData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "검색 결과 없음", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        val nextPosition = mList.size // 다음 위치는 현재 리스트의 크기와 같습니다.
        mList.add(FoodData("피자"))
        mList.add(FoodData("치킨"))
        mList.add(FoodData("떡볶이"))
        mList.add(FoodData("김치볶음밥"))
        mList.add(FoodData("짜장면"))
        mList.add(FoodData("짬뽕"))
        mList.add(FoodData("탕수육"))
        mList.add(FoodData("초코우유"))
        mList.add(FoodData("딸기우유"))

        // 새로운 음식을 현재 리스트의 다음 위치에 추가
        mList.add(nextPosition, FoodData("새로운음식"))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleImage() {
        Log.d("toggle","함수출력")
        if (isImage1Visible) {
            imageView1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.group_124))
            Log.d("toggle","이미지1로 변경")

        } else {
            imageView1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.group_126))
            Log.d("toggle","이미지2로 변경")
        }

        isImage1Visible = !isImage1Visible
        imageView1.invalidate()
    }

    private fun deleteData(){

        val swipegesture = object : SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction:Int){
                when(direction){
                    ItemTouchHelper.LEFT ->{
                        adapter.deleteItem(viewHolder.absoluteAdapterPosition)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView((recyclerView))
    }

    override fun onPause() {
        super.onPause()
        adapter.saveData(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.loadData(this)
    }
}