package com.racpan.gp_assessment.data_model.rocket.first_stage

import java.io.Serializable

data class Cores(
    val coreSerial : String?,
    val flight : Int?,
    val block : String?,
    val gridfins : Boolean?,
    val legs : Boolean?,
    val reused : Boolean?,
    val landSuccess : Boolean?,
    val landingIntent : Boolean?,
    val landingType : String?,
    val landingVehicle : String?) : Serializable