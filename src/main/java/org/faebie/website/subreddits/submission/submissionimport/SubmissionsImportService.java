package org.faebie.website.subreddits.submission.submissionimport;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.faebie.website.subreddits.model.ImportService;
import org.faebie.website.subreddits.submission.ImmutableSubmission;
import org.faebie.website.subreddits.submission.Submission;
import org.faebie.website.subreddits.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubmissionsImportService extends ImportService {

    private final SubmissionRepository submissionRepository;

    public SubmissionsImportService(@Value("${submission.importPath}") final String importPath,
                                    @Value("${submission.importGlob}") final String importGlob,
                                    @Value("${submission.importDLQPath}") final String importDLQPath,
                                    final SubmissionRepository submissionRepository) {
        this.importInputPath = importPath;
        this.importGlob = importGlob;
        this.importDLQPath = importDLQPath;
        this.submissionRepository = submissionRepository;
    }

    protected void importFile(final Path entry) {
        final List<SubmissionImportDTO> submissions = new ArrayList<>();

        String line = "";
        try (final FileReader reader = new FileReader(entry.toString());
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            log.info("Importing submissions from file {}", entry);
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    final SubmissionImportDTO submissionDTO = OBJECT_MAPPER.readValue(line, SubmissionImportDTO.class);
                    importSubmission(submissionDTO);
                } catch (final JsonProcessingException e) {
                    log.error("Could not deserialize {} to Submission", line, e);
                    log.warn("Continuing with submission deserialization...");
                }
            }
        }  catch (final FileNotFoundException e) {
            log.error("Could not find file at path {}", entry);
            throw new RuntimeException(e);
        } catch (final IOException e) {
            log.error("Failed to read file at path {}", entry);
            throw new RuntimeException(e);
        }
    }

    private void importSubmission(final SubmissionImportDTO submissionDTO) {
        log.info("Importing submission {}", submissionDTO.title());
        final Submission submission =
                ImmutableSubmission.builder()
                        .id(submissionDTO.id())
                        .submissionId(submissionDTO.submissionId())
                        .subreddit(submissionDTO.subreddit())
                        .title(submissionDTO.title())
                        .text(submissionDTO.text())
                        .author(submissionDTO.author())
                        .url(submissionDTO.url())
                        .permalink(submissionDTO.permalink())
                        .score(submissionDTO.score())
                        .created(submissionDTO.created())
                        .build();
        submissionRepository.save(submission);
    }
}
