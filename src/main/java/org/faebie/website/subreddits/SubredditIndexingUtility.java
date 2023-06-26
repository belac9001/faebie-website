package org.faebie.website.subreddits;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.model.Comment;
import org.faebie.website.subreddits.model.Submission;
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
    }

    public static List<Submission> deserializeSubmissions(final String path) {
        final List<Submission> submissions = new ArrayList<>();

        String line = "";
        try (final FileReader reader = new FileReader(path);
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    submissions.add(OBJECT_MAPPER.readValue(line, Submission.class));
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
        } catch (final OutOfMemoryError e) {
            log.error("Ran out of memory when deserializing submissions", e);
            log.error("Returning the deserialized submissions");
        }

        return submissions;
    }

    /**
     * TODO: Optimize this to prevent OutOfMemory exception
     */
    public static List<Comment> deserializeComments(final String path) {
        final List<Comment> comments = new ArrayList<>();

        String line = "";
        try (final FileReader reader = new FileReader(path);
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    comments.add(OBJECT_MAPPER.readValue(line, Comment.class));
                } catch (final JsonProcessingException e) {
                    log.error("Could not deserialize {} to Comment", line, e);
                    log.warn("Continuing with comment deserialization...");
                    throw new RuntimeException(e);
                }
            }
        }  catch (final FileNotFoundException e) {
            log.error("Could not find file at path {}", path);
            throw new RuntimeException(e);
        } catch (final IOException e) {
            log.error("Failed to read file at path {}", path);
            throw new RuntimeException(e);
        }

        return comments;
    }

    private SubredditIndexingUtility() {
        // prevent instantiation
    }
}
