package com.example.alomtest.food
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R

class FoodAdapter(private var mList : MutableList<FoodData>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){


    inner class FoodViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
        fun bind(data: FoodData){
            titleTv.text = data.title
        }
    }
    //class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        //private val rvTitle: TextView = itemView.findViewById(R.id.food_text)
      //  fun bind(data: DataClass){
        //    rvTitle.text = data.dataTitle
      //  }

   // }
   interface OnItemClickListener{
       fun onItemClick(position:Int)
   }
   private var itemClickListener: FoodAdapter.OnItemClickListener?=null
   fun setOnItemClickListener(listener: FoodAdapter.OnItemClickListener){
       this.itemClickListener = listener
   }


fun setFilteredList(mList: List<FoodData>) {
    this.mList = mList.toMutableList()
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)


    }}


    override fun getItemCount(): Int {
        return mList.size
    }


    //삭제
    fun deleteItem(i : Int){
        mList.removeAt(i)
        notifyDataSetChanged()
    }}

//    fun saveData(context: Context) {
//        val preferences = context.getSharedPreferences("food_data", Context.MODE_PRIVATE)
//        val editor = preferences.edit()
//        val jsonStringList = mList.map { it.toJsonString()}
//        editor.putStringSet("food_list", jsonStringList.toSet())
//        editor.apply()
//    }
//
//    fun loadData(context: Context) {
//        val preferences = context.getSharedPreferences("food_data", Context.MODE_PRIVATE)
//        val jsonStringList = preferences.getStringSet("food_list", emptySet())
//        mList = jsonStringList?.map { FoodData.fromString(it) }?.toMutableList() ?: mutableListOf()
//        notifyDataSetChanged()
//    }

//}