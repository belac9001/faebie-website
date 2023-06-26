package org.faebie.website;

import org.faebie.website.subreddits.IndexRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteApplication {

	public static void main(String[] args) {
		System.out.println("loading subreddits...");
		IndexRepository.loadSubreddits();

		System.out.println("running spring application...");
		SpringApplication.run(WebsiteApplication.class, args);
	}

}
