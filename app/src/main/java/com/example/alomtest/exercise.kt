package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.databinding.FragmentExerciseBinding
import com.example.alomtest.food.mainpage.AdapterClass
import com.example.alomtest.food.mainpage.DataClass

class exercise : Fragment() {
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList:ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentExerciseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        imageList = arrayOf(
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//            R.drawable.ic_launcher_foreground,
//
//        )
//        titleList = arrayOf(
//            "헬스",
//            "복싱",
//            "필라테스",
//            "달리기",
//            "수영",
//            "등산",
//            "기타 다른운동"
//        )
//
//        recyclerView = view.findViewById(R.id.rv_food)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.setHasFixedSize(true)
//        dataList = arrayListOf<DataClass>()
//        getData()
//
//
//    }
//
//    private fun getData(){
//        for(i in titleList.indices){
//            val dataClass = DataClass(titleList[i])
//            dataList.add(dataClass)
//        }
//        recyclerView.adapter = AdapterClass(dataList)
//    }

}}