package com.Satellite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class SatelliteSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatelliteSchoolApplication.class, args);
	}

}