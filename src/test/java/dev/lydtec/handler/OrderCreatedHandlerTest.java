package dev.lydtec.handler;

import dev.lydtec.dispatch.handler.OrderCreatedHandler;
import dev.lydtec.dispatch.service.DispatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderCreatedHandlerTest {

    @Mock
    private DispatchService dispatchService;

    @InjectMocks
    private OrderCreatedHandler orderCreatedHandler;

    @Test
    void listen() {
        orderCreatedHandler.listen("payload");
        Mockito.verify(dispatchService,Mockito.times(1)).process("payload");
    }
}