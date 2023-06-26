package org.faebie.website.subreddits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.model.Submission;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    private static final Logger log = LogManager.getLogger(IndexService.class);
    private static final String SUBMISSIONS_PATH = "./src/main/resources/data/subreddits/submissions/DMAcademy_submissions";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Submission> getIndexedSubreddits() {
        final List<Submission> submissions = deserializeSubmissions(SUBMISSIONS_PATH, 10);

        for (final Submission submission : submissions) {
            final String title = submission.title();
            log.info(title);
        }

        return submissions;
    }

    private List<Submission> deserializeSubmissions(final String path, final int limit) {
        final List<Submission> submissions = new ArrayList<>();

        try (final FileReader reader = new FileReader(path);
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (submissions.size() == limit)
                    break;

                submissions.add(objectMapper.readValue(line, Submission.class));
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return submissions;
    }
}
