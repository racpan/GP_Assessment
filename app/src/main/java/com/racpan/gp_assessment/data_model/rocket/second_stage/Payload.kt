package com.racpan.gp_assessment.data_model.rocket.second_stage

import java.io.Serializable

data class Payload(
    val payloadId: String?,
    val noradId: Array<Int?>?,
    val reused: Boolean?,
    val customers: Array<String?>?,
    val nationality: String?,
    val manufacturer: String?,
    val payloadType: String?,
    val payloadMassKG: Double?,
    val payloadMassLbs: Int?,
    val orbit: String?,
    val orbitParams: OrbitParams?) : Serializable