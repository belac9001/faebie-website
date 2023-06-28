package org.faebie.website.subreddits.submission;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmissionRepository {
    private static final Logger log = LogManager.getLogger(SubmissionRepository.class);

    public SubmissionRepository() {
    }

    @Transactional
    public void save(final Submission submission) {
        log.info("Saving submission with id {} to database...", submission.id());
    }

    @Transactional(readOnly = true)
    public Integer count() {
        return -1;
    }
}
