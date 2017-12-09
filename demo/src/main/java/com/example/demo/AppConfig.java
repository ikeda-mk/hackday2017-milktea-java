package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	TalkingTextHolder  talkingTextHolder() {
		return new TalkingTextHolder();
	}

	@Bean
	ShiritoriResponseDto responceDto() {
		return new ShiritoriResponseDto();
	}

}
