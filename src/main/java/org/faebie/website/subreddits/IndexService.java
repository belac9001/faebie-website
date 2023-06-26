package org.faebie.website.subreddits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.model.Submission;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    private static final Logger log = LogManager.getLogger(IndexService.class);
    private static final String SUBMISSIONS_PATH = "./src/main/resources/data/subreddits/submissions/DMAcademy_submissions";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<String> getIndexedSubreddits() {
        final List<Submission> submissions = deserializeSubmissions(SUBMISSIONS_PATH, 10);
        //final List<Submission> submissions = objectMapper.readValue(new File(SUBMISSIONS_PATH), typeReference);

        for (final Submission submission : submissions) {
            log.info(submission.title());
        }

        return List.of("sub1", "sub2");
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
