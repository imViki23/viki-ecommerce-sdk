package com.viki.api.catalog.configs;

import com.viki.api.common.dtos.DebeziumDto;
import com.viki.api.catalog.dtos.DebeziumProductDto;
import com.viki.api.catalog.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.MessageChannel;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@EnableIntegration
@Configuration
@RequiredArgsConstructor
@Slf4j
public class IntegrationConfig {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @Bean
    public MessageChannel productInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow catalogProcessingFlow(ConsumerFactory<String, String> consumerFactory) {
        return IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(
                        consumerFactory,
                        "catalog_server.catalog.products",
                        "catalog_server.catalog.product_variants"
                ))
                .channel("productInputChannel")
                .transform(payload -> {
                    try {
                        JavaType targetType = objectMapper.getTypeFactory()
                                .constructParametricType(DebeziumDto.class, DebeziumProductDto.class);
                        String jsonString = (payload instanceof byte[]) ? new String((byte[]) payload) : (String) payload;
                        return objectMapper.readValue(jsonString, targetType);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse Debezium JSON payload", e);
                    }
                })
                .handle(message -> {
                    @SuppressWarnings("unchecked")
                    DebeziumDto<DebeziumProductDto> debeziumDto = (DebeziumDto<DebeziumProductDto>) message.getPayload();
                    UUID productId = debeziumDto.getPayload().getAfter().getProductId();
                    productService.evictProductCache(productId);
                })
                .get();
    }
}
