package com.example.demo.customer.validation;

import com.example.demo.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "customer")
@Setter
@Getter
@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = "classpath:customer.yml",
        ignoreResourceNotFound = true
)
public class CustomerProperties {

    private CustomerAttributeProperties attribute;
}
