package dev.lydtec.dispatch.service;

import dev.lydtec.dispatch.message.OrderCreated;
import dev.lydtec.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void process(OrderCreated orderCreated) throws Exception{
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderID())
                .build();
        kafkaTemplate.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
