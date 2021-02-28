package com.inabottle.points.domain.model.treasure

import java.time.Instant
import java.util.*

 data class TreasureHunt(
         val id: UUID,
         val steps: List<Step>,
         val maxWinners : Int?,
         val startDate : Instant,
         val endDate : Instant,
         val userCreateId : UUID,
)