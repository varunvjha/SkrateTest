package com.example.skratetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobPostingsAdapter: RecyclerView.Adapter<JobsViewHolder>() {
    private val items: ArrayList<JobPosting> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobsViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.role.text = currentItem.role
        holder.companyName.text = currentItem.organization_name
        holder.date.text = currentItem.date_posted
        holder.location.text = currentItem.location
    }

    fun updateJobs(updatedJobs: ArrayList<JobPosting>) {
        items.clear()
        items.addAll(updatedJobs)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class JobsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val role: TextView = itemView.findViewById(R.id.role)
    val location: TextView = itemView.findViewById(R.id.location)
    val date: TextView = itemView.findViewById(R.id.date)
    val companyName: TextView = itemView.findViewById(R.id.company)
}