package com.moshood.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.moshood.core.model.Mapped;
import com.moshood.shell.exceptions.CustomExceptionResolver;


@Configuration
public class ProjectConfig {

	@Bean
	Mapped noMapped() {
		Mapped mapped = new Mapped();
		mapped.setGivenName(null);
		mapped.setURL(null);
		return mapped;
	}
	
	@Bean(name = "moshood")
	@Primary
	Mapped moshoodMapped() {
		Mapped mapped = new Mapped();
		mapped.setGivenName("google");
		mapped.setURL("https://www.google.com/");
		return mapped;
	}
	
	@Bean
	CustomExceptionResolver customExceptionResolver() {
		return new CustomExceptionResolver();
	}
}
