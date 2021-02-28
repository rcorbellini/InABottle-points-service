package com.inabottle.treasure.infrastructure.adapters

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

interface EventProducer{
    fun send(exchange : String, routingKey : String, obj : Any)
}


@Component
class  RabbitProducer(private val rabbitTemplate : RabbitTemplate) : EventProducer{
    override fun send(exchange: String, routingKey: String, obj: Any) =
        rabbitTemplate.convertAndSend(exchange,   routingKey ,  obj)
}
