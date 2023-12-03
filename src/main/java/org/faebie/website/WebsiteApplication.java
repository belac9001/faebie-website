package org.faebie.website;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.SubredditIndexingUtility;
import org.faebie.website.subreddits.comment.commentimport.CommentImportDTO;
import org.faebie.website.subreddits.submission.submissionimport.SubmissionImportDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class WebsiteApplication {
	private static final Logger log = LogManager.getLogger(WebsiteApplication.class);
	private static final String SUBMISSIONS_PATH = "./src/main/resources/data/subreddits/submissions/DMAcademy_submissions";
	private static final String COMMENTS_PATH = "./src/main/resources/data/subreddits/comments/DMAcademy_comments";

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
		/**

		log.info("deserializing submissions...");
		long start = System.currentTimeMillis();
		final List<SubmissionImportDTO> submissions = SubredditIndexingUtility.deserializeSubmissions(SUBMISSIONS_PATH);
		long end = System.currentTimeMillis();
		log.info("deserialized {} submissions in {}ms", submissions.size(), end - start);

		log.info("deserializing comments...");
		start = System.currentTimeMillis();
		final List<CommentImportDTO> comments = SubredditIndexingUtility.deserializeComments(COMMENTS_PATH);
		end = System.currentTimeMillis();
		log.info("deserialized {} comments in {}ms", comments.size(), end - start);
		**/
	}

	@Bean
	CommandLineRunner loadSubreddits() {
		log.info("loading subreddits...");
		return args -> SubredditIndexingUtility.loadSubreddits();
	}

}
