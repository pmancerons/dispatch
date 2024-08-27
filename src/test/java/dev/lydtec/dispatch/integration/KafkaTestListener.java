package dev.lydtec.dispatch.integration;

import dev.lydtec.dispatch.message.OrderDispatched;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class KafkaTestListener {

    private final static String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    AtomicInteger dispatchPreparingCounter = new AtomicInteger(0);
    AtomicInteger orderDispatchedCounter = new AtomicInteger(0);


    @KafkaListener(groupId = "KafkaIntegrationTest", topics = ORDER_DISPATCHED_TOPIC)
    void receiveOrderDispatched(@Payload OrderDispatched payload) {
        log.debug("Received OrderDispatched: " + payload);
        orderDispatchedCounter.incrementAndGet();
    }
}
