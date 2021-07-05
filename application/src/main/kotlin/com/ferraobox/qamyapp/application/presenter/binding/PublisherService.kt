package com.ferraobox.qamyapp.application.presenter.binding
/**
 *
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.io.Serializable


@Service
class PublisherService (
    private val streamBridge: StreamBridge
){
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
    fun send(event: Serializable, binding: String?) {
        log.debug("sending {}", event)
        val msg = MessageBuilder.withPayload(event).build()
        streamBridge.send(binding, msg)
    }

}

 **/