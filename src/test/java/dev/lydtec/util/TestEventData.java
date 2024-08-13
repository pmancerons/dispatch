package dev.lydtec.util;

import dev.lydtec.dispatch.message.OrderCreated;

import java.util.UUID;

public class TestEventData {
    public static OrderCreated buildOrderCreatedEvent(UUID orderId, String item){
        return OrderCreated.builder()
                .orderID(orderId)
                .item(item)
                .build();
    }
}
