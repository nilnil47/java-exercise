package com.hendisantika.postgres;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication implements CommandLineRunner {

	@Value("${spring.datasource.url}")
	private String userBucketPath;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("----------------------------------------------");
		System.out.println(userBucketPath);
		System.out.println("----------------------------------------------");
	}
}