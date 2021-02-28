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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface PointService{
    fun save(model: Point) : Mono<Point>

    fun finAll() : Flux<Point>
}

@Service
class PointServiceImp(private val pointRepository: PointRepository) : PointService  {
    override fun save(model: Point): Mono<Point> = pointRepository.save(model)

    override fun finAll(): Flux<Point>  = pointRepository.findAll()

}