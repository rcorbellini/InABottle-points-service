package com.inabottle.points.application.rest

import com.inabottle.points.domain.model.points.Point
import com.inabottle.points.domain.model.treasure.TreasureHunt
import com.inabottle.points.domain.serivce.ProcessService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
class ProcessController(
        @Qualifier(ProcessService.POINTS_QUALIFIER) private val processPoints: ProcessService<TreasureHunt> )  {

    @PostMapping("/process/treasure", produces = [MediaType.TEXT_EVENT_STREAM_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun save(@RequestBody treasureHuntInsertRequest : TreasureHunt) : Mono<Point>{
        return processPoints.process(treasureHuntInsertRequest)
    }


}
