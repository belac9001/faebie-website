package org.faebie.website.subreddits.model;
import org.springframework.stereotype.Component;

@Component
public class Submission {
    private final String id;
    private final Subreddit subreddit;
    private final String title;
    private final String text;
    private final String author;
    private final long created; // Time created in UTC
    private final boolean isVideo;
    private final String permalink; // "/r/<SUBREDDIT>/comments/<SUBMISSION_ID>/this_is_a_submission_permalink/"
    private final int score;
    private final String url;
}
