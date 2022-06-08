package com.thomaswesselink.homeserver;

import com.thomaswesselink.homeserver.dao.TodoDao;
import com.thomaswesselink.homeserver.model.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeserverApplication.class, args);
	}
}
