package com.hackathon.nullnullteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NullnullteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(NullnullteamApplication.class, args);
	}

}
