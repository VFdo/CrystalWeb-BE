package com.groupp.crystalweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrystalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrystalWebApplication.class, args);
	}

}
