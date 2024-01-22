package com.example.alomtest.food

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.alomtest.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


 abstract class SwipeGesture(context : Context) : ItemTouchHelper.Callback {
    private val context: Context = context

    private val deleteColor = ContextCompat.getColor(context, R.color.red)
    private val deleteIcon = R.drawable.delete

     override fun getMovementFlags(
         recyclerView: RecyclerView,
         viewHolder: RecyclerView.ViewHolder
     ): Int {
         return makeMovementFlags(0, ItemTouchHelper.LEFT)
     }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
     override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
         when (direction) {
             ItemTouchHelper.LEFT -> {
                 onSwipeLeft(viewHolder.bindingAdapterPosition)
             }
         }
     }

    override fun onChildDraw(

        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(Context,c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addBackgroundColor(deleteColor)
            .addActionIcon(deleteIcon)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
     abstract fun onSwipeLeft(position:Int)
}