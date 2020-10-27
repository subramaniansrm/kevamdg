package com.kevamdg.sr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.kevamdg.sr.security.HttpAuthenticationEntryPoint;



@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		
		/*http
        .cors()
            .and()
        .csrf()
            .disable()
        .authorizeRequests()
            .antMatchers("/**")
                .permitAll()
            .anyRequest()
                .authenticated();*/
		
		http.requestMatchers().and().authorizeRequests()
		.antMatchers("/kevamdg/**").authenticated()
		.antMatchers("/kevamdg/**").permitAll()
		.antMatchers("/kevamaterial/**").authenticated()
		.antMatchers("/kevamaterial/**").permitAll()
		.antMatchers("/kiosk/**").authenticated()
		.antMatchers("/kiosk/**").permitAll();

				
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(customAuthEntryPoint());
	}

	@Bean
	public AuthenticationEntryPoint customAuthEntryPoint() {
		return new HttpAuthenticationEntryPoint();
	}

}