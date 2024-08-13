package dev.lydtec.handler;

import dev.lydtec.dispatch.handler.OrderCreatedHandler;
import dev.lydtec.dispatch.message.OrderCreated;
import dev.lydtec.dispatch.service.DispatchService;
import dev.lydtec.util.TestEventData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class OrderCreatedHandlerTest {

    @Mock
    private DispatchService dispatchService;

    @InjectMocks
    private OrderCreatedHandler orderCreatedHandler;

    @Test
    void listen() {
        var orderCreated = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(),UUID.randomUUID().toString());
        orderCreatedHandler.listen(orderCreated);
        Mockito.verify(dispatchService,Mockito.times(1)).process(orderCreated);
    }
}