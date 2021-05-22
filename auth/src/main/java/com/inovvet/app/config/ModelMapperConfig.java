package com.inovvet.app.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper model = new ModelMapper();
		model.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
		.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
		return model;
	}
}
