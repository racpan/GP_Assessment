package com.racpan.gp_assessment.parser

import android.content.Context
import com.racpan.gp_assessment.R
import com.racpan.gp_assessment.data_model.*
import com.racpan.gp_assessment.data_model.rocket.Fairings
import com.racpan.gp_assessment.data_model.rocket.Rocket
import com.racpan.gp_assessment.data_model.rocket.first_stage.Cores
import com.racpan.gp_assessment.data_model.rocket.first_stage.FirstStage
import com.racpan.gp_assessment.data_model.rocket.second_stage.OrbitParams
import com.racpan.gp_assessment.data_model.rocket.second_stage.Payload
import com.racpan.gp_assessment.data_model.rocket.second_stage.SecondStage
import org.json.JSONArray
import org.json.JSONObject

class JSONToModel {

    private lateinit var c : Context

    fun jsonToLaunchData(obj : JSONObject, c : Context) : LaunchData {
        this.c = c
        return LaunchData(try {obj.getInt(c.getString(R.string.flight_number))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.mission_name))}catch(e : Exception){null},
                            try {jsonToStringArray(obj.getJSONArray(c.getString(R.string.mission_id)))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.upcoming))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.launch_year))}catch(e : Exception){null},
                            try {obj.getInt(c.getString(R.string.launch_date_unix))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.launch_date_utc))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.launch_date_local))}catch(e : Exception){null},
                            try {obj.getBoolean(c.getString(R.string.is_tentative))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.tentative_max_precision))}catch(e : Exception){null},
                            try {obj.getBoolean(c.getString(R.string.tbd))}catch(e : Exception){null},
                            try {obj.getInt(c.getString(R.string.launch_window))}catch(e : Exception){null},
                            try {jsonToStringArray(obj.getJSONArray(c.getString(R.string.ships)))}catch(e : Exception){null},
                            try {obj.getBoolean(c.getString(R.string.launch_success))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.crew))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.details))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.static_fire_date_utc))}catch(e : Exception){null},
                            try {obj.getInt(c.getString(R.string.static_fire_date_unix))}catch(e : Exception){null},
                            try {jsonToTelemetry(obj.getJSONObject(c.getString(R.string.telemetry)))}catch(e : Exception){null},
                            try {jsonToLaunchSite(obj.getJSONObject(c.getString(R.string.launch_site)))}catch(e : Exception){null},
                            try {jsonToLaunchFailureDetails(obj.getJSONObject(c.getString(R.string.launch_failure_details)))}catch(e : Exception){null},
                            try {jsonToTimeline(obj.getJSONObject(c.getString(R.string.timeline)))}catch(e : Exception){null},
                            try {jsonToLinks(obj.getJSONObject(c.getString(R.string.links)))}catch(e : Exception){null},
                            try {jsonToRocket(obj.getJSONObject(c.getString(R.string.rocket)))}catch(e : Exception){null})
    }

    private fun jsonToStringArray(obj : JSONArray) : Array<String?> {
        var arr = arrayOfNulls<String>(obj.length())
        for (i in 0 until obj.length()) {
            arr[i] = obj.getString(i)
        }
        return arr
    }

    private fun jsonToIntArray(obj : JSONArray) : Array<Int?> {
        var arr = arrayOfNulls<Int>(obj.length())
        for (i in 0 until obj.length()) {
            arr[i] = obj.getInt(i)
        }
        return arr
    }

    private fun jsonToTelemetry(obj : JSONObject) : Telemetry {
        return Telemetry(try {obj.getString(c.getString(R.string.flight_club))}catch(e : Exception){null})
    }

    private fun jsonToLaunchSite(obj : JSONObject) : LaunchSite {
        return LaunchSite(try {obj.getString(c.getString(R.string.site_id))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.site_name))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.site_name_long))}catch(e : Exception){null})
    }

    private fun jsonToLaunchFailureDetails(obj : JSONObject) : LaunchFailureDetails {
        return LaunchFailureDetails(try {obj.getDouble(c.getString(R.string.time))}catch(e : Exception){null},
                                    try {obj.getDouble(c.getString(R.string.altitude))}catch(e : Exception){null},
                                    try {obj.getString(c.getString(R.string.reason))}catch(e : Exception){null})
    }

    private fun jsonToTimeline(obj : JSONObject) : Timeline {
        return Timeline(try {obj.getDouble(c.getString(R.string.webcast_liftoff))}catch(e : Exception){null})
    }

    private fun jsonToLinks(obj : JSONObject) : Links {
        return Links(try {obj.getString(c.getString(R.string.mission_patch))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.mission_patch_small))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.reddit_campaign))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.reddit_launch))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.reddit_recovery))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.reddit_media))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.presskit))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.article_link))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.wikipedia))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.video_link))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.youtube_id))}catch(e : Exception){null},
                        try {jsonToStringArray(obj.getJSONArray(c.getString(R.string.flickr_images)))}catch(e : Exception){null})
    }

    private fun jsonToRocket(obj : JSONObject) : Rocket {
        return Rocket(try {obj.getString(c.getString(R.string.rocket_id))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.rocket_name))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.rocket_type))}catch(e : Exception){null},
                        try {jsonToFirstStage(obj.getJSONObject(c.getString(R.string.first_stage)))}catch(e : Exception){null},
                        try {jsonToSecondStage(obj.getJSONObject(c.getString(R.string.second_stage)))}catch(e : Exception){null},
                        try {jsonToFairings(obj.getJSONObject(c.getString(R.string.fairings)))}catch(e : Exception){null})
    }

    private fun jsonToFirstStage(obj : JSONObject) : FirstStage {
        return FirstStage(try {jsonToCores(obj.getJSONArray(c.getString(R.string.cores)))}catch(e : Exception){null})
    }

    private fun jsonToCores(obj : JSONArray) : Array<Cores?> {
        var arr = arrayOfNulls<Cores>(obj.length())
        for (i in 0 until obj.length()) {
            val sarr = obj.getJSONObject(i)
            val farr = Cores(try {sarr.getString(c.getString(R.string.core_serial))}catch(e : Exception){null},
                            try {sarr.getInt(c.getString(R.string.flight))}catch(e : Exception){null},
                            try {sarr.getString(c.getString(R.string.block))}catch(e : Exception){null},
                            try {sarr.getBoolean(c.getString(R.string.gridfins))}catch(e : Exception){null},
                            try {sarr.getBoolean(c.getString(R.string.legs))}catch(e : Exception){null},
                            try {sarr.getBoolean(c.getString(R.string.reused))}catch(e : Exception){null},
                            try {sarr.getBoolean(c.getString(R.string.land_success))}catch(e : Exception){null},
                            try {sarr.getBoolean(c.getString(R.string.landing_intent))}catch(e : Exception){null},
                            try {sarr.getString(c.getString(R.string.landing_type))}catch(e : Exception){null},
                            try {sarr.getString(c.getString(R.string.landing_vehicle))}catch(e : Exception){null})
            arr[i] = farr
        }
        return arr
    }

    private fun jsonToSecondStage(obj : JSONObject) : SecondStage {
        return SecondStage(try {obj.getInt(c.getString(R.string.block))}catch(e : Exception){null},
                            try {jsonToPayload(obj.getJSONArray(c.getString(R.string.payloads)))}catch(e : Exception){null})
    }

    private fun jsonToPayload(obj : JSONArray) : Array<Payload?> {
        var arr = arrayOfNulls<Payload>(obj.length())
        for (i in 0 until obj.length()) {
            val parr = obj.getJSONObject(i)
            val farr = Payload(try {parr.getString(c.getString(R.string.payload_id))}catch(e : Exception){null},
                                try {jsonToIntArray(parr.getJSONArray(c.getString(R.string.norad_id)))}catch(e : Exception){null},
                                try {parr.getBoolean(c.getString(R.string.reused))}catch(e : Exception){null},
                                try {jsonToStringArray(parr.getJSONArray(c.getString(R.string.customers)))}catch(e : Exception){null},
                                try {parr.getString(c.getString(R.string.nationality))}catch(e : Exception){null},
                                try {parr.getString(c.getString(R.string.manufacturer))}catch(e : Exception){null},
                                try {parr.getString(c.getString(R.string.payload_type))}catch(e : Exception){null},
                                try {parr.getDouble(c.getString(R.string.payload_mass_kg))}catch(e : Exception){null},
                                try {parr.getInt(c.getString(R.string.payload_mass_lbs))}catch(e : Exception){null},
                                try {parr.getString(c.getString(R.string.orbit))}catch(e : Exception){null},
                                try {jsonToOrbitParams(parr.getJSONObject(c.getString(R.string.orbit_params)))}catch(e : Exception){null})
            arr[i] = farr
        }
        return arr
    }

    private fun jsonToOrbitParams(obj : JSONObject) : OrbitParams {
        return OrbitParams(try {obj.getString(c.getString(R.string.reference_system))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.regime))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.longitude))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.semi_major_axis_km))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.eccentricity))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.periapsis_km))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.apoapsis_km))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.inclination_deg))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.period_min))}catch(e : Exception){null},
                            try {obj.getInt(c.getString(R.string.lifespan_years))}catch(e : Exception){null},
                            try {obj.getString(c.getString(R.string.epoch))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.mean_motion))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.raan))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.arg_of_pericenter))}catch(e : Exception){null},
                            try {obj.getDouble(c.getString(R.string.mean_anomaly))}catch(e : Exception){null})
    }

    private fun jsonToFairings(obj : JSONObject) : Fairings {
        return Fairings(try {obj.getBoolean(c.getString(R.string.reused))}catch(e : Exception){null},
                        try {obj.getBoolean(c.getString(R.string.recovery_attempt))}catch(e : Exception){null},
                        try {obj.getBoolean(c.getString(R.string.recovered))}catch(e : Exception){null},
                        try {obj.getString(c.getString(R.string.ship))}catch(e : Exception){null})
    }
}