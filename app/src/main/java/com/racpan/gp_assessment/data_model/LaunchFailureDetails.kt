package com.racpan.gp_assessment.data_model

import java.io.Serializable

data class LaunchFailureDetails(
    val time : Double?,
    val altitude : Double?,
    val reason : String?) : Serializable