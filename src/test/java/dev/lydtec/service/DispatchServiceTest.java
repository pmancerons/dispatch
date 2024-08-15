package dev.lydtec.service;

import dev.lydtec.dispatch.message.OrderCreated;
import dev.lydtec.dispatch.message.OrderDispatched;
import dev.lydtec.dispatch.service.DispatchService;
import dev.lydtec.util.TestEventData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class DispatchServiceTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private DispatchService service;


    @Test
    void process_Success() throws Exception{
        when(kafkaTemplate.send(anyString(), any(OrderDispatched.class)))
                .thenReturn(mock(CompletableFuture.class));

        service.process(OrderCreated.builder().build());

        verify(kafkaTemplate,times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));
    }


    @Test
    void process_ProducerThrowsAnException(){
        OrderCreated orderCreated = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), UUID.randomUUID().toString());

        doThrow(new RuntimeException("producer failure"))
                .when(kafkaTemplate)
                .send(anyString(), any(OrderDispatched.class));

        Exception e = Assertions.assertThrows(RuntimeException.class,() -> service.process(orderCreated) );

        verify(kafkaTemplate,times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));

        Assertions.assertEquals("producer failure", e.getMessage());

    }
}