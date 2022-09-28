package com.racpan.gp_assessment.data_model

import com.racpan.gp_assessment.data_model.rocket.Rocket
import java.io.Serializable

data class LaunchData (
    val flightNum: Int?,
    val missionName: String?,
    val missionId: Array<String?>?,
    val upcoming: String?,
    val launchYear: String?,
    val launchDateUnix: Int?,
    val launchDateUTC: String?,
    val launchDateLocal: String?,
    val isTentative: Boolean?,
    val tentativeMaxPrecision: String?,
    val tbd: Boolean?,
    val launchWindow: Int?,
    val ships: Array<String?>?,
    val launchSuccess: Boolean?,
    val crew: String?,
    val details: String?,
    val staticFireDateUTC: String?,
    val staticFireDateUnix: Int?,
    val telemetry: Telemetry?,
    val launchSite: LaunchSite?,
    val launchFailureDetails: LaunchFailureDetails?,
    val timeline: Timeline?,
    val links: Links?,
    val rocket: Rocket?) : Serializable
