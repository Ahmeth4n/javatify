package com.magnakod;

import com.magnakod.emulator.Spotify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories({"com.magnakod.repository"})
@EntityScan({"com.magnakod.entity"})
@ComponentScan({"com.magnakod.emulator", "com.magnakod.controller","com.magnakod.web",
		"com.magnakod.schedulers","com.magnakod.config","com.magnakod.service"})
@EnableAsync
@EnableConfigurationProperties
@ServletComponentScan
@EnableScheduling
public class SpotifyPlatformApplication {

	@Autowired
	public Spotify spotify;
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public static Spotify getSpotifyBean(){
		return new Spotify();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpotifyPlatformApplication.class, args);
	}

}
