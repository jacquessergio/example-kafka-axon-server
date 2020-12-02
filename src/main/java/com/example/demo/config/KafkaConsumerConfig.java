package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.axonframework.extensions.kafka.autoconfig.KafkaAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.domain.dto.ClientDTO;

@EnableKafka
@Configuration
@AutoConfigureAfter({ KafkaAutoConfiguration.class })
public class KafkaConsumerConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value("${kafka.group.id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000");
		props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");
		
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean("consumerFactoryClientDTO")
	public ConsumerFactory<String, ClientDTO> consumerFactoryClientDTO() {
		return new DefaultKafkaConsumerFactory<>(consumerFactory().getConfigurationProperties(),
				new StringDeserializer(), new JsonDeserializer<>(ClientDTO.class));
	}

	@Bean("kafkaListenerContainerFactoryClientDTO")
	public ConcurrentKafkaListenerContainerFactory<String, ClientDTO> kafkaListenerContainerFactoryClientDTO() {
		final ConcurrentKafkaListenerContainerFactory<String, ClientDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactoryClientDTO());
		return factory;
	}
}
