package com.racpan.gp_assessment.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.racpan.gp_assessment.LaunchDetails
import com.racpan.gp_assessment.R
import com.racpan.gp_assessment.data_model.LaunchData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class LaunchDataRecycleViewAdapter(private val dataList: MutableList<LaunchData>) : RecyclerView.Adapter<LaunchDataRecycleViewAdapter.ViewHolder>(){

    private val imageScope = CoroutineScope(Dispatchers.Main)
    private lateinit var c : Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val missionName: TextView
        val rocketName : TextView
        val launchSiteName : TextView
        val dateOfLaunch : TextView
        val launchImage : ImageView

        init {
            missionName = view.findViewById(R.id.textMissionName)
            rocketName = view.findViewById(R.id.textRocketName)
            launchSiteName = view.findViewById(R.id.textLaunchSiteName)
            dateOfLaunch = view.findViewById(R.id.textDateOfLaunch)
            launchImage = view.findViewById(R.id.imageLaunchImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_item, parent, false)
        c = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.missionName.text = dataList[position]?.missionName
        holder.rocketName.text = dataList[position]?.rocket?.rocketName
        holder.launchSiteName.text = dataList[position]?.launchSite?.siteName
        holder.dateOfLaunch.text = dataList[position]?.launchDateLocal

        imageScope.launch {
            val urlString = dataList[position]?.links?.missionPatchSmall
            var bMap : Bitmap
            if (urlString == null) {
                holder.launchImage.setImageResource(R.drawable.resource_default)
            } else {
                try {
                    withContext(Dispatchers.IO) {
                        val imageUrl = URL(dataList[position]?.links?.missionPatchSmall)
                        val httpconn = imageUrl.openConnection() as HttpURLConnection
                        bMap = BitmapFactory.decodeStream(httpconn.inputStream)
                        httpconn.disconnect()
                    }
                    holder.launchImage.setImageBitmap(bMap)
                } catch (e : IOException) {
                    holder.launchImage.setImageResource(R.drawable.resource_default)
                }
            }
        }

        holder.missionName.setOnClickListener(setClickListener(dataList[position]))
        holder.rocketName.setOnClickListener(setClickListener(dataList[position]))
        holder.launchSiteName.setOnClickListener(setClickListener(dataList[position]))
        holder.dateOfLaunch.setOnClickListener(setClickListener(dataList[position]))
        holder.launchImage.setOnClickListener(setClickListener(dataList[position]))
    }

    override fun getItemCount() = dataList.size

    private fun setClickListener(launchData : LaunchData) : OnClickListener {
        return OnClickListener {
            val intent = Intent(c, LaunchDetails::class.java).apply {
                putExtra(c.getString(R.string.launch_data), launchData as Serializable)
            }
            c.startActivity(intent)
        }
    }
}