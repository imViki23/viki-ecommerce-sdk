package com.viki.api.datasource.configs;

import com.viki.api.common.configs.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@AutoConfiguration
@PropertySource(value = "classpath:application-datasource.yaml", factory = YamlPropertySourceFactory.class)
public class DatasourceAutoConfiguration {
}
