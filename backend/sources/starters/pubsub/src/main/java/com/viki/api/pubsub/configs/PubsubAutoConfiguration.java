package com.viki.api.pubsub.configs;

import com.viki.api.common.configs.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource(value = "classpath:application-pubsub.yml", factory = YamlPropertySourceFactory.class)
public class PubsubAutoConfiguration {
}
