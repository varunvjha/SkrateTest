package com.example.skratetest

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemMoveCallback(adapter: ItemTouchHelperContract) : ItemTouchHelper.Callback() {

    private final val mAdapter: ItemTouchHelperContract = adapter

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mAdapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE) {
            val myViewHolder: StatsViewHolder = viewHolder as StatsViewHolder
            mAdapter.onRowSelected(myViewHolder)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is StatsViewHolder) {
            val myViewHolder: StatsViewHolder =
                viewHolder
            mAdapter.onRowClear(myViewHolder)
        }

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }

    interface ItemTouchHelperContract {
        fun onRowMoved(fromPosition:Int, toPosition:Int)
        fun onRowSelected(myViewHolder: StatsViewHolder)
        fun onRowClear(myViewHolder: StatsViewHolder)
    }

}