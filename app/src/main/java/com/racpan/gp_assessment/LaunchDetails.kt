package com.racpan.gp_assessment

import android.app.ActionBar.LayoutParams
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.racpan.gp_assessment.data_model.LaunchData
import com.racpan.gp_assessment.data_model.rocket.first_stage.Cores
import com.racpan.gp_assessment.data_model.rocket.second_stage.OrbitParams
import com.racpan.gp_assessment.data_model.rocket.second_stage.Payload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class LaunchDetails : AppCompatActivity() {

    private val imageScope = CoroutineScope(Dispatchers.Main)
    private lateinit var launchData: LaunchData
    private lateinit var launchDetails : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_details)

        launchDetails = findViewById(R.id.launch_details)
        launchData = intent.extras?.get(getString(R.string.launch_data)) as LaunchData

        createTextTitle(getString(R.string.flight_number))
        createTextDetails(launchData.flightNum.toString())

        createTextTitle(getString(R.string.mission_name))
        createTextDetails(launchData.missionName)

        createTextTitle(getString(R.string.mission_id))
        createArrayString(launchData.missionId)

        createTextTitle(getString(R.string.upcoming))
        createTextDetails(launchData.upcoming)

        createTextTitle(getString(R.string.launch_year))
        createTextDetails(launchData.launchYear)

        createTextTitle(getString(R.string.launch_date_unix))
        createTextDetails(launchData.launchDateUnix.toString())

        createTextTitle(getString(R.string.launch_date_utc))
        createTextDetails(launchData.launchDateUTC)

        createTextTitle(getString(R.string.launch_date_local))
        createTextDetails(launchData.launchDateLocal)

        createTextTitle(getString(R.string.is_tentative))
        createTextDetails(launchData.isTentative.toString())

        createTextTitle(getString(R.string.tentative_max_precision))
        createTextDetails(launchData.tentativeMaxPrecision)

        createTextTitle(getString(R.string.tbd))
        createTextDetails(launchData.tbd.toString())

        createTextTitle(getString(R.string.launch_window))
        createTextDetails(launchData.launchWindow.toString())

        createTextTitle(getString(R.string.rocket))
        createTextDetails(getString(R.string.rocket_id) + " : " + launchData.rocket?.rocketId)
        createTextDetails(getString(R.string.rocket_name) + " : " + launchData.rocket?.rocketName)
        createTextDetails(getString(R.string.rocket_type) + " : " + launchData.rocket?.rocketType)
        createTextDetails(getString(R.string.rocket_id) + " : " + launchData.rocket?.rocketId)
        createTextDetails(getString(R.string.first_stage) + " : " + getString(R.string.cores))
        if (!launchData.rocket?.firstStage?.cores.isNullOrEmpty()) {
            for (core in launchData.rocket?.firstStage?.cores!!) {
                if (core != null) {
                    createArrayFromCores(core)
                }
            }
        }
        createTextTitle(getString(R.string.second_stage))
        createTextDetails(getString(R.string.block) + " : " + launchData.rocket?.secondStage?.block)
        createTextDetails(getString(R.string.payloads))
        if (!launchData.rocket?.secondStage?.payloads.isNullOrEmpty()) {
            for (payload in launchData.rocket?.secondStage?.payloads!!) {
                if (payload != null) {
                    createArrayFromPayload(payload)
                    createTextTitle(getString(R.string.orbit_params))
                    payload.orbitParams?.let { createArrayFromOrbitParams(it) }
                }
            }
        }
        createTextTitle(getString(R.string.fairings))
        createTextDetails(getString(R.string.reused) + " : " + launchData.rocket?.fairings?.reused)
        createTextDetails(getString(R.string.recovery_attempt) + " : " + launchData.rocket?.fairings?.reused)
        createTextDetails(getString(R.string.recovered) + " : " + launchData.rocket?.fairings?.reused)
        createTextDetails(getString(R.string.ship) + " : " + launchData.rocket?.fairings?.reused)

        createTextTitle(getString(R.string.ships))
        createArrayString(launchData.ships)

        createTextTitle(getString(R.string.telemetry))
        createTextDetails(getString(R.string.flight_club) + " : " + launchData.telemetry?.flightClub)

        createTextTitle(getString(R.string.launch_site))
        createTextDetails(getString(R.string.site_id) + " : " + launchData.launchSite?.siteId)
        createTextDetails(getString(R.string.site_name) + " : " + launchData.launchSite?.siteName)
        createTextDetails(getString(R.string.site_name_long) + " : " + launchData.launchSite?.siteNameLong)

        createTextTitle(getString(R.string.launch_success))
        createTextDetails(launchData.launchSuccess.toString())

        createTextTitle(getString(R.string.launch_failure_details))
        createTextDetails(getString(R.string.time) + " : " + launchData.launchFailureDetails?.time)
        createTextDetails(getString(R.string.altitude) + " : " + launchData.launchFailureDetails?.altitude)
        createTextDetails(getString(R.string.reason) + " : " + launchData.launchFailureDetails?.reason)

        createTextTitle(getString(R.string.links))
        createTextDetails(getString(R.string.mission_patch))
        launchData.links?.missionPatch?.let { createImage(it) }
        createTextDetails(getString(R.string.mission_patch_small))
        launchData.links?.missionPatchSmall?.let { createImage(it) }
        createTextDetails(getString(R.string.reddit_campaign) + " : " + launchData.links?.redditCampaign)
        createTextDetails(getString(R.string.reddit_launch) + " : " + launchData.links?.redditLaunch)
        createTextDetails(getString(R.string.reddit_recovery) + " : " + launchData.links?.redditRecovery)
        createTextDetails(getString(R.string.reddit_media) + " : " + launchData.links?.redditMedia)
        createTextDetails(getString(R.string.presskit) + " : " + launchData.links?.presskit)
        createTextDetails(getString(R.string.article_link) + " : " + launchData.links?.articleLink)
        createTextDetails(getString(R.string.wikipedia) + " : " + launchData.links?.wikipedia)
        createTextDetails(getString(R.string.video_link) + " : " + launchData.links?.videoLink)
        createTextDetails(getString(R.string.youtube_id) + " : " + launchData.links?.youtubeId)
        createTextDetails(getString(R.string.flickr_images))
        if (!launchData.links?.flickrImage.isNullOrEmpty()) {
            for (img in launchData.links?.flickrImage!!) {
                if (img != null) {
                    createImage(img)
                }
            }
        }

        createTextTitle(getString(R.string.details))
        createTextDetails(launchData.details)

        createTextTitle(getString(R.string.static_fire_date_utc))
        createTextDetails(launchData.staticFireDateUTC)

        createTextTitle(getString(R.string.static_fire_date_unix))
        createTextDetails(launchData.staticFireDateUnix.toString())

        createTextTitle(getString(R.string.timeline))
        createTextDetails(getString(R.string.webcast_liftoff) + " : " + launchData.timeline?.webcastLiftoff)

        createTextTitle(getString(R.string.crew))
        createTextDetails(launchData.crew)

    }

    private fun createTextTitle(title : String?) {
        var titleView = TextView(this)
        val arr = title?.split("_")
        var str = ""
        if (arr != null) {
            for (i in arr.indices) {
                str += arr[i].replaceFirstChar { it.uppercase() } + " "
            }
        }
        titleView.text = str
        titleView.textSize = 16f
        titleView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        titleView.setPadding(2, 2, 2, 2)
        titleView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        titleView.typeface = Typeface.DEFAULT_BOLD
        launchDetails.addView(titleView)
    }

    private fun createTextDetails(details : String?) {
        var detailsView = TextView(this)
        detailsView.text = details
        detailsView.textSize = 14f
        detailsView.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        detailsView.setPadding(20, 5, 5, 5)
        detailsView.layoutParams  = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        detailsView.typeface = Typeface.DEFAULT_BOLD
        launchDetails.addView(detailsView)
    }

    private fun createArrayString(strArr : Array<String?>?) {
        var str = ""
        if (strArr != null) {
            for (i in strArr.indices) {
                str += strArr[i] + ","
            }
        }
        createTextDetails(str.dropLast(1))
    }

    private fun createArrayFromInt(intArr : Array<Int?>?) {
        var str = ""
        if (intArr != null) {
            for (i in intArr.indices) {
                str += intArr[i].toString() + ","
            }
        }
        createTextDetails(str.dropLast(1))
    }

    private fun createArrayFromCores(core : Cores) {
        createTextDetails(getString(R.string.core_serial) + " : " + core.coreSerial)
        createTextDetails(getString(R.string.flight) + " : " + core.flight)
        createTextDetails(getString(R.string.block) + " : " + core.block)
        createTextDetails(getString(R.string.gridfins) + " : " + core.gridfins)
        createTextDetails(getString(R.string.legs) + " : " + core.legs)
        createTextDetails(getString(R.string.reused) + " : " + core.reused)
        createTextDetails(getString(R.string.land_success) + " : " + core.landSuccess)
        createTextDetails(getString(R.string.landing_intent) + " : " + core.landingIntent)
        createTextDetails(getString(R.string.landing_type) + " : " + core.landingType)
        createTextDetails(getString(R.string.landing_vehicle) + " : " + core.landingVehicle)
    }

    private fun createArrayFromPayload(payload : Payload) {
        createTextDetails(getString(R.string.payload_id) + " : " + payload.payloadId)
        createTextDetails(getString(R.string.norad_id) + " : " + createArrayFromInt(payload.noradId))
        createTextDetails(getString(R.string.reused) + " : " + payload.reused)
        createTextDetails(getString(R.string.customers) + " : " + createArrayString(payload.customers))
        createTextDetails(getString(R.string.nationality) + " : " + payload.nationality)
        createTextDetails(getString(R.string.manufacturer) + " : " + payload.manufacturer)
        createTextDetails(getString(R.string.payload_type) + " : " + payload.payloadType)
        createTextDetails(getString(R.string.payload_mass_kg) + " : " + payload.payloadMassKG)
        createTextDetails(getString(R.string.payload_mass_lbs) + " : " + payload.payloadMassLbs)
        createTextDetails(getString(R.string.orbit) + " : " + payload.orbit)
    }

    private fun createArrayFromOrbitParams(op : OrbitParams) {
        createTextDetails(getString(R.string.reference_system) + " : " + op.referenceSystem)
        createTextDetails(getString(R.string.regime) + " : " + op.regime)
        createTextDetails(getString(R.string.longitude) + " : " + op.longitude)
        createTextDetails(getString(R.string.semi_major_axis_km) + " : " + op.semiMajorAxisKM)
        createTextDetails(getString(R.string.eccentricity) + " : " + op.eccentricity)
        createTextDetails(getString(R.string.periapsis_km) + " : " + op.periapsisKM)
        createTextDetails(getString(R.string.apoapsis_km) + " : " + op.apoapsisKM)
        createTextDetails(getString(R.string.inclination_deg) + " : " + op.inclinationDeg)
        createTextDetails(getString(R.string.period_min) + " : " + op.periodMin)
        createTextDetails(getString(R.string.lifespan_years) + " : " + op.lifespanYears)
        createTextDetails(getString(R.string.epoch) + " : " + op.epoch)
        createTextDetails(getString(R.string.mean_motion) + " : " + op.meanMotion)
        createTextDetails(getString(R.string.raan) + " : " + op.raan)
        createTextDetails(getString(R.string.arg_of_pericenter) + " : " + op.argOfPericenter)
        createTextDetails(getString(R.string.mean_anomaly) + " : " + op.meanAnomaly)
    }

    private fun createImage(imgUrl : String) {
        var imgView = ImageView(this)
        imgView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imgView.scaleType = ImageView.ScaleType.FIT_CENTER
        imgView.adjustViewBounds = true
        imageScope.launch {
            val urlString = imgUrl
            var bMap : Bitmap
            if (!(urlString == null || urlString.isBlank())) {
                try {
                    withContext(Dispatchers.IO) {
                        val imageUrl = URL(imgUrl)
                        val httpconn = imageUrl.openConnection() as HttpURLConnection
                        bMap = BitmapFactory.decodeStream(httpconn.inputStream)
                        httpconn.disconnect()
                    }
                    imgView.setImageBitmap(bMap)
                    launchDetails.addView(imgView)
                } catch (e : IOException) { }
            }
        }
    }
}