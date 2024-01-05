package com.example.alomtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent
import org.w3c.dom.Text


class AdapterClass (private val dataList:ArrayList<DataClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val ITEM =0
    val FOOTER=1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ITEM ->{val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,
                parent, false)
                ViewHolderClass(itemView)
            }
            FOOTER ->{val view = LayoutInflater.from(parent.context).inflate(R.layout.itemfooter_layout, parent, false)
                FooterViewHolderClass(view, parent.context)
            }
            else -> throw IllegalArgumentException("Invalid view type")

            }
        }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM->{
                val itemViewHolder = holder as ViewHolderClass

                itemViewHolder.bind(dataList[position])

            }
            FOOTER->{val FooterViewHolder = holder as FooterViewHolderClass
            FooterViewHolder.bindFooterData()}



    }

    }

    override fun getItemCount(): Int {
        return dataList.size +1
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



    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val rvTitle: TextView = itemView.findViewById(R.id.food_text)
        fun bind(data: DataClass){
            rvTitle.text = data.dataTitle
        }

    }
}
