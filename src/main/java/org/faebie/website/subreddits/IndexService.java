package org.faebie.website.subreddits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.comment.commentimport.CommentImportDTO;
import org.faebie.website.subreddits.submission.submissionimport.SubmissionImportDTO;
import org.faebie.website.subreddits.model.Subreddit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    private static final Logger log = LogManager.getLogger(IndexService.class);


    public List<SubmissionImportDTO> getSubmissionsForSubreddit(final Subreddit subreddit) {
        final List<SubmissionImportDTO> submissions = List.of();

        return submissions;
    }

    public List<CommentImportDTO> getCommentsForSubmission(final String submissionId) {
        final List<CommentImportDTO> comments = List.of();

        return comments;
    }
}
