package com.inabottle.treasure.infrastructure.adapters

import com.inabottle.points.domain.model.treasure.TreasureHunt
import org.springframework.stereotype.Component


interface TreasureHuntProducer{
    fun treasureHuntSaved(model : TreasureHunt)
}

@Component
class TreasureHuntProducerImp(private val producer: EventProducer) : TreasureHuntProducer {

    override fun treasureHuntSaved(entity: TreasureHunt){
        producer.send(EXCHANGE,   ROUTING_KEY ,  entity)
    }

    companion object{
        const val EXCHANGE = "treasure-hunt"
        const val ROUTING_KEY = "treasure-hunt.created"
    }
}