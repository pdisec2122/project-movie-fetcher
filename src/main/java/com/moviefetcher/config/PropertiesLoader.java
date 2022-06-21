package com.moviefetcher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:application.properties")
public class PropertiesLoader {
}
