package com.example.alomtest.food.mainpage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentFoodBinding
import com.example.alomtest.food.foodcustom01.AddActivity
import com.example.alomtest.food.foodcustom01.AddActivity.Companion.RESULT_ADD_TASK
import com.example.alomtest.food.foodcustom01.EditActivity


class Food : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList:ArrayList<DataClass>
    lateinit var titleList:Array<String>
    private lateinit var adapter: AdapterClass
    private val REQUEST_CODE_EDIT = 100





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFoodBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleList = arrayOf(

        )

        recyclerView = view.findViewById(R.id.rv_food)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()

        adapter = AdapterClass(dataList)

        adapter.setOnItemClickListener(object: AdapterClass.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(requireContext(), EditActivity::class.java)
                intent.putExtra("dataTitle", dataList[position].dataTitle)
                requestEditLauncher.launch(intent)
            }

        })




        adapter.setOnFooterClickListener(object : AdapterClass.OnFooterClickListener {
            override fun FooterClick(position:Int){

                if(position == adapter.itemCount-1){
                    val intent = Intent(requireContext(), AddActivity::class.java)
                    requestLauncher.launch(intent)
                }
            }
        }

        )
        adapter.setOnDeleteClickListener(object:AdapterClass.OnDeleteClickListener{
        override fun onDeleteClick(position: Int) {
            // 이 곳에 삭제 이벤트를 처리하는 코드를 추가하세요
            dataList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    })

        recyclerView.adapter = adapter



    }







    override fun onResume() {
        super.onResume()


    }


    private val requestEditLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val editedDataTitle = result.data?.getStringExtra("editedDataTitle")
            handleEditResult(editedDataTitle)
        }
    }
    private fun handleEditResult(editedDataTitle: String?) {
        val position = findPositionByDataTitle(editedDataTitle)
        if (position != -1) {
            // 데이터 갱신
            dataList[position].dataTitle = editedDataTitle ?: ""
            adapter.notifyItemChanged(position)
        }
    }
//before
    private val requestLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode== AddActivity.RESULT_ADD_TASK){
                val newTask = result.data?.getStringExtra("newTask")
                newTask?.let{
                    dataList.add(DataClass(it))
                    adapter.notifyItemInserted(dataList.size-1)
                }
            }
        }




//


    private fun getData(){
        for(i in titleList.indices){
            val dataClass = DataClass(titleList[i])
            dataList.add(dataClass)
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null

    }

    private fun findPositionByDataTitle(dataTitle: String?): Int {
        for (i in dataList.indices) {
            if (dataList[i].dataTitle == dataTitle) {
                return i
            }
        }
        return -1
    }
}

