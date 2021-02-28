package com.inabottle.points.application.rest

import com.inabottle.points.domain.model.points.Point
import com.inabottle.points.domain.serivce.PointService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class PointsController( private val processPoints: PointService)  {

    @GetMapping("/points", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun findAll() : Flux<Point>{
        return processPoints.finAll()
    }


}
