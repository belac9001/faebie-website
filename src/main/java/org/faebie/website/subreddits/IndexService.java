package org.faebie.website.subreddits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.model.Comment;
import org.faebie.website.subreddits.model.Submission;
import org.faebie.website.subreddits.model.Subreddit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    private static final Logger log = LogManager.getLogger(IndexService.class);


    public List<Submission> getSubmissionsForSubreddit(final Subreddit subreddit) {
        final List<Submission> submissions = List.of();

        return submissions;
    }

    public List<Comment> getCommentsForSubmission(final String submissionId) {
        final List<Comment> comments = List.of();

        return comments;
    }
}
