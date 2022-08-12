package com.example.skratetest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
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
        if (currentItem.session_type == "Review") {
            holder.sessionCard.setCardBackgroundColor(Color.parseColor("#91AEE1"))
        } else {
            holder.sessionCard.setCardBackgroundColor(Color.parseColor("#E8C9D1"))
        }
    }

    fun updateSessions(updatedSessions: ArrayList<UpcomingSession>, shuffle: Boolean) {
        items.clear()
        if (shuffle) {
            updatedSessions.shuffle()
        }
        items.addAll(updatedSessions)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class SessionsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val sessionCard:CardView = itemView.findViewById(R.id.session_type_card)
    val mentorName:TextView = itemView.findViewById(R.id.mentor_name)
    val timings:TextView = itemView.findViewById(R.id.timings)
    val date:TextView = itemView.findViewById(R.id.date)
    val sessionType: TextView = itemView.findViewById(R.id.session_type)
}
