package com.racpan.gp_assessment.data_model.rocket

import com.racpan.gp_assessment.data_model.rocket.first_stage.FirstStage
import com.racpan.gp_assessment.data_model.rocket.second_stage.SecondStage
import java.io.Serializable

data class Rocket(
    val rocketId : String?,
    val rocketName : String?,
    val rocketType : String?,
    val firstStage : FirstStage?,
    val secondStage: SecondStage?,
    val fairings: Fairings?) : Serializable