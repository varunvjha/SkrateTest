package com.example.skratetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UpcomingSessionsAdapter: RecyclerView.Adapter<SessionsViewHolder>() {
    private val items: ArrayList<UpcomingSession> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return SessionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SessionsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.mentorName.text = currentItem.mentor_name
        holder.timings.text = currentItem.timings
        holder.date.text = currentItem.date
        holder.sessionType.text = currentItem.session_type
    }

    fun updateSessions(updatedSessions: ArrayList<UpcomingSession>) {
        items.clear()
        items.addAll(updatedSessions)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SessionsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val mentorName:TextView = itemView.findViewById(R.id.mentor_name)
    val timings:TextView = itemView.findViewById(R.id.timings)
    val date:TextView = itemView.findViewById(R.id.date)
    val sessionType: TextView = itemView.findViewById(R.id.session_type)
}
