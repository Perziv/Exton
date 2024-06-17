package net.parziv.exton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ExtonWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExtonWebApplication.class, args);
	}
}
