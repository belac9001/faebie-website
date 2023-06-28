package org.faebie.website.subreddits;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.comment.commentimport.CommentImportDTO;
import org.faebie.website.subreddits.submission.submissionimport.SubmissionImportDTO;
import org.faebie.website.subreddits.model.Subreddit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SubredditIndexingUtility {

    private static final Logger log = LogManager.getLogger(SubredditIndexingUtility.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static void loadSubreddits() {
        IndexRepository.addSubreddit(new Subreddit("DMAcademy"));
        log.info("Loaded {} subreddits", IndexRepository.getAllSubreddits().size());
    }

    public static List<SubmissionImportDTO> deserializeSubmissions(final String path) {
        final List<SubmissionImportDTO> submissions = new ArrayList<>();

        String line = "";
        try (final FileReader reader = new FileReader(path);
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    submissions.add(OBJECT_MAPPER.readValue(line, SubmissionImportDTO.class));
                } catch (final JsonProcessingException e) {
                    log.error("Could not deserialize {} to Submission", line, e);
                    log.warn("Continuing with submission deserialization...");
                }
            }
        }  catch (final FileNotFoundException e) {
            log.error("Could not find file at path {}", path);
            throw new RuntimeException(e);
        } catch (final IOException e) {
            log.error("Failed to read file at path {}", path);
            throw new RuntimeException(e);
        }

        return submissions;
    }

    /**
     * TODO: Optimize this to prevent OutOfMemory exception
     */
    public static List<CommentImportDTO> deserializeComments(final String path) {
        final List<CommentImportDTO> comments = new ArrayList<>();

        int linesRead = 0;
        String line = "";
        try (final FileReader reader = new FileReader(path);
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((line = bufferedReader.readLine()) != null) {
                log.info(line);
                /**
                try {
                    comments.add(OBJECT_MAPPER.readValue(line, Comment.class));
                } catch (final JsonProcessingException e) {
                    log.error("Could not deserialize {} to Comment", line, e);
                    log.warn("Continuing with comment deserialization...");
                    throw new RuntimeException(e);
                }**/
            }
        }  catch (final FileNotFoundException e) {
            log.error("Could not find file at path {}", path);
            throw new RuntimeException(e);
        } catch (final IOException e) {
            log.error("Failed to read file at path {}", path);
            throw new RuntimeException(e);
        }

        return List.of();
    }

    private SubredditIndexingUtility() {
        // prevent instantiation
    }
}
