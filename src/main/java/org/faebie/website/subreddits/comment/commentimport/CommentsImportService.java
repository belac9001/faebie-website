package org.faebie.website.subreddits.comment.commentimport;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.faebie.website.subreddits.comment.Comment;
import org.faebie.website.subreddits.comment.ImmutableComment;
import org.faebie.website.subreddits.comment.CommentRepository;
import org.faebie.website.subreddits.model.ImportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class CommentsImportService extends ImportService {
    private final CommentRepository commentRepository;

    public CommentsImportService(@Value("${comment.importPath}") final String importPath,
                                    @Value("${comment.importGlob}") final String importGlob,
                                    @Value("${comment.importDLQPath}") final String importDLQPath,
                                    final CommentRepository commentRepository) {
        this.importInputPath = importPath;
        this.importGlob = importGlob;
        this.importDLQPath = importDLQPath;
        this.commentRepository = commentRepository;
    }

    protected void importFile(final Path entry) {

        String line = "";
        try (final FileReader reader = new FileReader(entry.toString());
             final BufferedReader bufferedReader = new BufferedReader(reader)) {
            log.info("Importing comments from file {}", entry);
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    final CommentImportDTO commentDTO = OBJECT_MAPPER.readValue(line, CommentImportDTO.class);
                    importComment(commentDTO);
                } catch (final JsonProcessingException e) {
                    log.error("Could not deserialize {} to Comment", line, e);
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

    private void importComment(final CommentImportDTO commentDTO) {
        final Comment comment = ImmutableComment.builder()
                .id(commentDTO.id())
                .subreddit(commentDTO.subreddit())
                .author(commentDTO.author())
                .body(commentDTO.body())
                .submissionId(commentDTO.submissionId())
                .parentId(commentDTO.parentId())
                .score(commentDTO.score())
                .ups(commentDTO.ups())
                .controversiality(commentDTO.controversiality())
                .created(commentDTO.created())
                .build();

        commentRepository.save(comment);
    }

}
