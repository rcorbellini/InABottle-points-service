package com.inabottle.points.infrastructure.repositories

import com.inabottle.points.domain.model.points.Point
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface PointRepository : ReactiveMongoRepository<Point, UUID>