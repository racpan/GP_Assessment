package com.racpan.gp_assessment.data_model.rocket.second_stage

import java.io.Serializable

data class SecondStage(
    val block: Int?,
    val payloads: Array<Payload?>?) : Serializable