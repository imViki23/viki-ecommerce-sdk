package com.viki.api.discovery.configs;

import com.viki.api.common.configs.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@EnableDiscoveryClient
@PropertySource(value = "classpath:application-discovery.yaml", factory = YamlPropertySourceFactory.class)
public class DiscoveryAutoConfiguration {
}
