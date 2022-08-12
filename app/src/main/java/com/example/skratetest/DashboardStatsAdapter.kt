package com.example.skratetest

import android.R.attr.data
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class DashboardStatsAdapter: RecyclerView.Adapter<StatsViewHolder>(), ItemMoveCallback.ItemTouchHelperContract{
    private val itemNames: ArrayList<String> = ArrayList()
    private val itemNumbers:ArrayList<Int> = ArrayList()
    private val items: HashMap<String, Int> = HashMap()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.itemName.text = itemNames[position]
        holder.itemNumber.text = items[itemNames[position]].toString()
    }

    override fun getItemCount(): Int {
        return itemNames.size
    }

    fun updateDashboard(dashboardStatsObject: DashboardStats, shuffle: Boolean) {
        itemNames.clear()
//        itemNumbers.clear()
        itemNames.add("Profile Views")
        itemNames.add("Mentorship Sessions")
        itemNames.add("Jobs Applied")
        itemNames.add("Skills Verified")
        if (shuffle) {
            itemNames.shuffle()
        }
        items.clear()
        items["Profile Views"] = dashboardStatsObject.profile_views
        items["Mentorship Sessions"] = dashboardStatsObject.mentorship_sessions
        items["Jobs Applied"] = dashboardStatsObject.jobs_applied
        items["Skills Verified"] = dashboardStatsObject.skills_verified
//        itemNumbers.add()
//        itemNumbers.add(dashboardStatsObject.mentorship_sessions)
//        itemNumbers.add(dashboardStatsObject.jobs_applied)
//        itemNumbers.add(dashboardStatsObject.skills_verified)
//        Log.d("itemnumbers", itemNumbers.toString())
        notifyDataSetChanged()
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition<toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemNames, i, i+1)
            }
        } else {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemNames, i, i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(myViewHolder: StatsViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(myViewHolder: StatsViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.parseColor("#F6F7FF"))
    }
}

class StatsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val itemName:TextView = itemView.findViewById(R.id.item_name)
    val itemNumber:TextView = itemView.findViewById(R.id.item_number)
}
