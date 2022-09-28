package com.racpan.gp_assessment.data_model.rocket.second_stage

import java.io.Serializable

data class OrbitParams(
    val referenceSystem : String?,
    val regime : String?,
    val longitude : Double?,
    val semiMajorAxisKM : Double?,
    val eccentricity : Double?,
    val periapsisKM : Double?,
    val apoapsisKM : Double?,
    val inclinationDeg : Double?,
    val periodMin : Double?,
    val lifespanYears : Int?,
    val epoch : String?,
    val meanMotion : Double?,
    val raan : Double?,
    val argOfPericenter : Double?,
    val meanAnomaly : Double?) : Serializable