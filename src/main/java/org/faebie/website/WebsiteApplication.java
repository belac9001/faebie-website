package org.faebie.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteApplication {

	public static void main(String[] args) {
		System.out.println("running spring application...");
		SpringApplication.run(WebsiteApplication.class, args);
	}

}
