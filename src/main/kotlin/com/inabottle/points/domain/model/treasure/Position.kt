package com.inabottle.points.domain.model.treasure

import java.math.BigDecimal


data class Position(
        val latitude : BigDecimal,
        val longitude: BigDecimal
){

    constructor( latitude : Double,
                 longitude: Double) : this(
            latitude = BigDecimal(latitude).setScale(SCALE),
            longitude= BigDecimal(longitude).setScale(SCALE)
    )

    init {
        require(latitude.toDouble() <= 90 && latitude.toDouble() >= -90){
                "value of latitude is not valid value"
        }

        require(longitude.toDouble() <= 180 && longitude.toDouble() >= -180){
            "value of longitude is not valid value"
        }
    }

    companion object{
        val SCALE = 4
    }
}

