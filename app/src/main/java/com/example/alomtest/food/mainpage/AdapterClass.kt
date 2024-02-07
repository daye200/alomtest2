package com.example.alomtest.food.mainpage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.food.foodcustom01.AddActivity
import com.example.alomtest.food.foodcustom02.FoodEditActivity


class AdapterClass (private val dataList:ArrayList<DataClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val ITEM =0
    val FOOTER=1
    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }
    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }
    interface OnReviseClickListener{
        fun ReviseClick(position: Int)
    }
    interface OnFooterClickListener{
        fun FooterClick(position:Int)
    }
    private var itemClickListener: OnItemClickListener?=null
    private var itemLongClickListener: OnItemLongClickListener?=null
    private var DeleteClickListener: OnDeleteClickListener?=null
    private var FooterClickListener: OnFooterClickListener?=null
    private var ReviseClickListener: OnReviseClickListener?=null


   // private var adapter: AdapterClass?=null

    fun removeItem(position: Int) {
        if (position < dataList.size) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataList.size)
       }
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ITEM ->{val itemView=LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent, false)
                ViewHolderClass(itemView)
            }
            FOOTER ->{val view = LayoutInflater.from(parent.context).inflate(R.layout.itemfooter_layout, parent, false)
                FooterViewHolderClass(view, parent.context)
            }
            else -> throw IllegalArgumentException("Invalid view type")

            }
        }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.itemClickListener = listener
    }
    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.itemLongClickListener = listener
    }
    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        this.DeleteClickListener = listener
    }
    fun setOnReviseClickListener(listener: OnReviseClickListener){
        this.ReviseClickListener = listener
    }
    fun setOnFooterClickListener(listener: OnFooterClickListener){
        this.FooterClickListener = listener
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM->{
                val itemViewHolder = holder as ViewHolderClass

               itemViewHolder.bind(dataList[position])
               itemViewHolder.itemView.setOnClickListener {
                  itemClickListener?.onItemClick(position)
                   val context = it.context
                   val dataTitle = dataList[position].dataTitle


                   // 커스텀 다이얼로그 표시
                   val customDialog = Food_dialog(context, dataTitle)
                   customDialog.show()
               }
                itemViewHolder.itemView.setOnLongClickListener{
                    itemLongClickListener?.onItemLongClick(position)
                    itemViewHolder.toggleButtonVisibility()
                    true
                }


            }
            FOOTER->{val footerViewHolder = holder as FooterViewHolderClass
            footerViewHolder.bindFooterData()
                footerViewHolder.itemView.setOnClickListener {
                    FooterClickListener?.FooterClick(dataList.size)
                }}



    }

    }

    override fun getItemCount(): Int {
        return dataList.size+1
    }
    override fun getItemViewType(position: Int): Int {
        return if (position < dataList.size) ITEM else FOOTER
    }
    inner class FooterViewHolderClass(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val footerImageView:ImageView
        init {
            val footerView = LayoutInflater.from(context).inflate(R.layout.itemfooter_layout, null)
            // 클릭 이벤트 처리
            footerImageView = footerView.findViewById((R.id.footerImageView))
            itemView.setOnClickListener {
                // Footer를 클릭했을 때 다른 화면으로 이동하는 코드
                val intent = Intent(context, AddActivity::class.java)

                context.startActivity(intent)
            }
        }
        fun bindFooterData(){
            footerImageView.setImageResource(R.drawable.food_item)
        }
    }



    inner class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val rvTitle: TextView = itemView.findViewById(R.id.food_text)
        private val buttonrevise: Button = itemView.findViewById(R.id.food_revisebutton) // 실제 버튼 ID로 대체하세요
        private val buttondelete: Button = itemView.findViewById(R.id.food_deletebutton)


        init {
            // 다른 초기화 또는 클릭 리스너를 추가하세요

            // 버튼 클릭 리스너 설정
            buttondelete.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // AdapterClass의 removeItem 메서드를 호출하여 아이템 삭제
                    DeleteClickListener?.onDeleteClick(position)

                }
            }
            buttonrevise.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    ReviseClickListener?.ReviseClick(position)
                }
            }
        }
        fun bind(data: DataClass){
            rvTitle.text = data.dataTitle
            buttonrevise.visibility = View.GONE
            buttondelete.visibility = View.GONE
        }
        fun toggleButtonVisibility() {
            buttonrevise.visibility = if (buttonrevise.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            buttondelete.visibility = if (buttondelete.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }


    }
}
