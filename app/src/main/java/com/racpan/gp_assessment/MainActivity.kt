package com.racpan.gp_assessment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.racpan.gp_assessment.adapters.LaunchDataRecycleViewAdapter
import com.racpan.gp_assessment.data_model.LaunchData
import com.racpan.gp_assessment.network.NetworkCalls
import com.racpan.gp_assessment.parser.JSONToModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var networkCalls : NetworkCalls
    private lateinit var launchData : JSONArray
    private lateinit var viewRecyclerLaunchData : RecyclerView
    private lateinit var textErrorMessage : TextView
    private val networkScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCalls = NetworkCalls()
        viewRecyclerLaunchData = findViewById(R.id.viewRecyclerLaunchData)
        textErrorMessage = findViewById(R.id.textErrorMessage)

        networkScope.launch {
            var result = ""
            var launchDataList : MutableList<LaunchData> = mutableListOf()
            try {
                withContext(Dispatchers.IO) {
                    result = networkCalls.getLaunchData(getString(R.string.api_url), applicationContext)
                    launchData = JSONArray(result)
                    for (i in 0 until launchData.length()) {
                        var jsonToModel = JSONToModel()
                        launchDataList.add(jsonToModel.jsonToLaunchData(launchData.getJSONObject(i), applicationContext))
                    }
                }
                launchDataList.sortByDescending { it.launchDateLocal }
                val rvAdapter = LaunchDataRecycleViewAdapter(launchDataList)
                viewRecyclerLaunchData.adapter = rvAdapter
                viewRecyclerLaunchData.layoutManager = LinearLayoutManager(applicationContext)
            } catch (ex : JSONException) {
                viewRecyclerLaunchData.visibility = View.GONE
                textErrorMessage.visibility = View.VISIBLE
                textErrorMessage.text = result
            }
        }
    }
}