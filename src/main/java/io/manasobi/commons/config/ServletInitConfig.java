package io.manasobi.commons.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import io.manasobi.AppRunner;

@Configuration
public class ServletInitConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppRunner.class, WebMvcConfig.class);
	}
}
