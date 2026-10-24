package com.viki.api.cache.configs;

import com.viki.api.common.configs.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource(value = "classpath:application-cache.yaml", factory = YamlPropertySourceFactory.class)
public class CacheAutoConfiguration {
}
