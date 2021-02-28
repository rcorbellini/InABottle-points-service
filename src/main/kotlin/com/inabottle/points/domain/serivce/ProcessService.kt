package com.inabottle.points.domain.serivce

import com.inabottle.points.domain.model.points.AmountPoints
import com.inabottle.points.domain.model.points.Point
import com.inabottle.points.domain.model.points.SourceTypes
import com.inabottle.points.domain.model.treasure.Step
import com.inabottle.points.domain.model.treasure.TreasureHunt
import com.inabottle.points.infrastructure.repositories.PointRepository
import org.apache.lucene.util.SloppyMath
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


interface ProcessService<T>{
    fun process(model: T) : Mono<Point>

    companion object{
        const val POINTS_QUALIFIER = "points"
    }
}

@Service
@Qualifier(ProcessService.POINTS_QUALIFIER)
class ProcessServiceImp(private val pointService: PointService ) : ProcessService<TreasureHunt> {

    override fun process(model: TreasureHunt) : Mono<Point> {
        val sumOfDistanceInMeters = model.steps.zipWithNext(::calculateDistanceBetweenStepsInMeters).sum()

        println(sumOfDistanceInMeters)

        val points = when (sumOfDistanceInMeters){
            in AmountPoints.VERY_LOW_AMOUNT.range ->   AmountPoints.VERY_LOW_AMOUNT.points
            in AmountPoints.LOW_AMOUNT.range ->  AmountPoints.LOW_AMOUNT.points
            in AmountPoints.MEDIUM_AMOUNT.range ->  AmountPoints.MEDIUM_AMOUNT.points
            in AmountPoints.HIGH_AMOUNT.range ->  AmountPoints.HIGH_AMOUNT.points
            in AmountPoints.VERR_HIGH_AMOUNT.range ->  AmountPoints.VERR_HIGH_AMOUNT.points
            else -> AmountPoints.NONE_AMOUNT.points
        }

        return pointService.save(Point(
                idSource = model.id,
                idUser = model.userCreateId,
                source =  SourceTypes.TREASURE_HUNT_CREATED.description,
                amount = points
        ))
    }


    private fun calculateDistanceBetweenStepsInMeters(prev : Step, next : Step): Double {
        val prevPos = prev.position
        val nextPos = next.position
        return SloppyMath.haversinMeters(prevPos.latitude.toDouble(), prevPos.longitude.toDouble(),
                nextPos.latitude.toDouble(), nextPos.longitude.toDouble())
    }

}