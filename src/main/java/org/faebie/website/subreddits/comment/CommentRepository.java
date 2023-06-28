package org.faebie.website.subreddits.comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentRepository {
    private static final Logger log = LogManager.getLogger(CommentRepository.class);

    public CommentRepository() {
    }

    @Transactional
    public void save(final Comment comment) {
        log.info("Saving comment with id {} to database...", comment.id());
    }

    @Transactional(readOnly = true)
    public Integer count() {
        return -1;
    }
}
