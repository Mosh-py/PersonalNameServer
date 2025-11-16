package com.moshood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class MyUrlMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyUrlMapperApplication.class, args);
	}
	
}
