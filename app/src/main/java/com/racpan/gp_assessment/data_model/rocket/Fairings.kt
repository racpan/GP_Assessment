package com.racpan.gp_assessment.data_model.rocket

import java.io.Serializable

data class Fairings(
    val reused : Boolean?,
    val recoveryAttempt : Boolean?,
    val recovered : Boolean?,
    val ship : String?) : Serializable