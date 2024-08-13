package dev.lydtec.dispatch;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@Slf4j
public class DispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("order.created")
				.partitions(10)
				.replicas(1)
				.build();
	}

}
