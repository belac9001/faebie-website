package org.faebie.website.subreddits.submission;

import org.faebie.website.subreddits.model.Subreddit;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
public interface Submission {
    String id();
    String submissionId();
    Subreddit subreddit();
    String title();
    String text();
    String author();
    String url();
    String permalink();
    int score();
    long created();
}
