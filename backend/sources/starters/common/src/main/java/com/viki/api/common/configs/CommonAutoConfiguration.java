package com.viki.api.common.configs;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource(value = "classpath:application-common.yaml", factory = YamlPropertySourceFactory.class)
public class CommonAutoConfiguration {
}
