package com.example.demo.config;

import java.util.Map;

import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.producer.ConfirmationMode;
import org.axonframework.extensions.kafka.eventhandling.producer.DefaultProducerFactory;
import org.axonframework.extensions.kafka.eventhandling.producer.KafkaPublisher;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(KafkaPublisher.class)
@EnableConfigurationProperties(KafkaProperties.class)
public class AxonConfig {

	private final KafkaProperties properties;

	public AxonConfig(KafkaProperties properties) {
		this.properties = properties;
	}
	
	/**
	 * This factory with the bean name "axonProducerFactory" 
	 * resolves the conflict with KafkaAutoConfigure.class
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ConditionalOnProperty("axon.kafka.producer.transaction-id-prefix")
	@Bean(name = "axonProducerFactory")
	public ProducerFactory kafkaProducerFactory() {
		final Map producer = properties.buildProducerProperties();
		final String transactionIdPrefix = properties.getProducer().getTransactionIdPrefix();
		if (transactionIdPrefix == null) {
			throw new IllegalStateException("transactionalIdPrefix cannot be empty");
		}
		return DefaultProducerFactory.builder()
									 .configuration(producer)
									 .confirmationMode(ConfirmationMode.TRANSACTIONAL)
									 .transactionalIdPrefix(transactionIdPrefix).build();
	}

	/*
	 * For Use event store in memory
	 * @Bean public EmbeddedEventStore eventStore() { return
	 * EmbeddedEventStore.builder().storageEngine(new
	 * InMemoryEventStorageEngine()).build(); }
	 */
}
