package com.inabottle.points.domain.model.points

import java.math.BigDecimal
import java.util.*

data class Point(
        val id: UUID = UUID.randomUUID(),
        val source: String,
        val idSource: UUID,
        val idUser: UUID,
        val amount: BigDecimal
)



interface IDescEnum {
    val description: String
}


enum class SourceTypes() : IDescEnum {
    TREASURE_HUNT_CREATED{
        override val description =  "TREASURE_HUNT_CREATED"
    }
}

interface IAmountPoints {
    val points: BigDecimal
    val range: ClosedFloatingPointRange<Double>
}


enum class AmountPoints() : IAmountPoints {
    VERY_LOW_AMOUNT{
        override val points =  BigDecimal(2)
        override val range = 0.0..200.0
    },
    LOW_AMOUNT{
        override val points =   BigDecimal(5)
        override val range = 200.0..400.0
    },
    MEDIUM_AMOUNT{
        override val points =  BigDecimal(12)
        override val range = 400.0..800.0
    },
    HIGH_AMOUNT{
        override val points =  BigDecimal(16)
        override val range = 800.0..1600.0
    },
    VERR_HIGH_AMOUNT{
        override val points =  BigDecimal(20)
        override val range = 1600.0..3200.0
    },
    NONE_AMOUNT{
        override val points =  BigDecimal.ZERO
        override val range = 640.0..Double.POSITIVE_INFINITY
    },
}