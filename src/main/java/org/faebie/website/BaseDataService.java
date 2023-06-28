package org.faebie.website;

import org.faebie.website.subreddits.comment.CommentRepository;
import org.faebie.website.subreddits.submission.SubmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseDataService {
    private static final Logger log = LoggerFactory.getLogger(BaseDataService.class);

    private final CommentRepository commentRepository;
    private final SubmissionRepository submissionRepository;

    public BaseDataService(
            final CommentRepository commentRepository,
            final SubmissionRepository submissionRepository) {
        this.commentRepository = commentRepository;
        this.submissionRepository = submissionRepository;
    }

    public void createDefaultData() {
        if (tablesAreEmpty()) {
            createSubmissions();
            createComments();
        }
    }

    private boolean tablesAreEmpty() {
        if (submissionRepository.count() > 0) {
            log.info("Submissions table not empty, skipping default data creation");
            return false;
        }
        if (commentRepository.count() > 0) {
            log.info("Comments table not empty, skipping default data creation");
            return false;
        }

        return true;
    }

    private void createSubmissions() {

    }

    private void createComments() {

    }
}
