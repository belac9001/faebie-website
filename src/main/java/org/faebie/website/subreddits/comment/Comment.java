package org.faebie.website.subreddits.comment;

import org.faebie.website.subreddits.model.Subreddit;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
public interface Comment {
    String id();
    String submissionId();
    String parentId();
    Subreddit subreddit();
    String body();
    String author();
    int score();
    int ups();
    int controversiality();
    long created();
}
