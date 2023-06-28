package org.faebie.website.subreddits;

import org.faebie.website.subreddits.model.Subreddit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Create database for subreddits/submissions/comments
 * This class is currently only acting as in-memory storage for subreddits/submissions/comments
 */
public final class IndexRepository {
    // list of subreddits
    // map of subreddit ids to submissions
    // map of submission ids to comments
    // map of parent ids to child ids
    private static final List<Subreddit> SUBREDDITS = new ArrayList<>();

    public static void addSubreddit(final Subreddit subreddit) {
        if (!SUBREDDITS.contains(subreddit))
            SUBREDDITS.add(subreddit);
    }

    public static Subreddit getSubredditFromName(final String name) {
        for (final Subreddit subreddit : SUBREDDITS) {
            if (name.equalsIgnoreCase(subreddit.getName()))
                return subreddit;
        }

        throw new IllegalArgumentException("Subreddit with name " + name + " not found.");
    }

    public static List<Subreddit> getAllSubreddits() {
        return SUBREDDITS;
    }

    private IndexRepository() {
        // prevent instantiation
    }
}
