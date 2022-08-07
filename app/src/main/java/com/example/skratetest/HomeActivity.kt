package com.example.skratetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class HomeActivity : AppCompatActivity() {
    private val url = "https://mocki.io/v1/bb11aecd-ba61-44b9-9e2c-beabc442d818"
    private lateinit var mDashboardStatsAdapter: DashboardStatsAdapter
    private lateinit var mUpcomingSessionsAdapter: UpcomingSessionsAdapter
    private lateinit var mJobPostingsAdapter: JobPostingsAdapter
    private lateinit var dashboardStatsRecyclerView: RecyclerView
    private lateinit var upcomingSessionsRecyclerView: RecyclerView
    private lateinit var jobsPostingsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        dashboardStatsRecyclerView = findViewById(R.id.dashboard_stats)
        dashboardStatsRecyclerView.layoutManager = LinearLayoutManager(this)
        upcomingSessionsRecyclerView = findViewById(R.id.upcoming_sessions)
        upcomingSessionsRecyclerView.layoutManager = LinearLayoutManager(this)
        jobsPostingsRecyclerView = findViewById(R.id.job_postings)
        jobsPostingsRecyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        mDashboardStatsAdapter = DashboardStatsAdapter()
        dashboardStatsRecyclerView.adapter = mDashboardStatsAdapter
        mUpcomingSessionsAdapter = UpcomingSessionsAdapter()
        upcomingSessionsRecyclerView.adapter = mUpcomingSessionsAdapter
        mJobPostingsAdapter = JobPostingsAdapter()
        jobsPostingsRecyclerView.adapter = mJobPostingsAdapter
    }

    private fun fetchData() {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
//                val fullName = it.getJSONObject("full_name")
                val dashboardStats = it.getJSONObject("dashboard_stats")
                Log.d("dashboardStats", dashboardStats.toString())
                val dashboardStatsObject = DashboardStats(
                    dashboardStats.getInt("profile_views"),
                    dashboardStats.getInt("mentorship_sessions"),
                    dashboardStats.getInt("jobs_applied"),
                    dashboardStats.getInt("skills_verified")
                )
                mDashboardStatsAdapter.updateDashboard(dashboardStatsObject)

                val upcomingSessionsArray = ArrayList<UpcomingSession>()
                val upcomingSessionsJsonArray = it.getJSONArray("upcoming_sessions")
                for (i in 0 until upcomingSessionsJsonArray.length()) {
                    val upcomingSessionsJsonObject = upcomingSessionsJsonArray.getJSONObject(i)
                    val upcomingSession = UpcomingSession(
                        upcomingSessionsJsonObject.getString("mentor_name"),
                        upcomingSessionsJsonObject.getString("timings"),
                        upcomingSessionsJsonObject.getString("date"),
                        upcomingSessionsJsonObject.getString("session_type")
                    )
                    upcomingSessionsArray.add(upcomingSession)
                }
                mUpcomingSessionsAdapter.updateSessions(upcomingSessionsArray)
                val jobPostingsArray = ArrayList<JobPosting>()
                val jobPostingsJsonArray = it.getJSONArray("job_postings")
                for (i in 0 until jobPostingsJsonArray.length()) {
                    val jobPostingsJsonObject = jobPostingsJsonArray.getJSONObject(i)
                    val jobPosting = JobPosting(
                        jobPostingsJsonObject.getString("role"),
                        jobPostingsJsonObject.getString("organization_name"),
                        jobPostingsJsonObject.getString("location"),
                        jobPostingsJsonObject.getString("date_posted")
                    )
                    jobPostingsArray.add(jobPosting)
                }
                mJobPostingsAdapter.updateJobs(jobPostingsArray)
            },
            {
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}