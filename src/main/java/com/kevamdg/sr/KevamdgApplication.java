package com.kevamdg.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories(basePackages = { "com.kevamdg.*" })
@ComponentScan(basePackages = { "com.kevamdg.**" })
@EntityScan(basePackages = { "com.kevamdg.sr.entity" })
@SpringBootApplication
public class KevamdgApplication extends SpringBootServletInitializer {
	static ConfigurableApplicationContext ctx = null;

	public static void main(String[] args) {

		try {
			SpringApplication.run(KevamdgApplication.class, args);
		} catch (Exception e) {
			ctx.close();
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KevamdgApplication.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
