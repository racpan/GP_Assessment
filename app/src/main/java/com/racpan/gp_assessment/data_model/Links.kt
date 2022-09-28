package com.racpan.gp_assessment.data_model

import java.io.Serializable

data class Links(
    val missionPatch: String?,
    val missionPatchSmall: String?,
    val redditCampaign: String?,
    val redditLaunch: String?,
    val redditRecovery: String?,
    val redditMedia: String?,
    val presskit: String?,
    val articleLink: String?,
    val wikipedia: String?,
    val videoLink: String?,
    val youtubeId: String?,
    val flickrImage: Array<String?>?) : Serializable