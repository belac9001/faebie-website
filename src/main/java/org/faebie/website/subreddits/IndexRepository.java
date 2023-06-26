package org.faebie.website.subreddits;

import org.faebie.website.subreddits.model.Subreddit;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Create database for subreddits/submissions/comments
 * Currently this is a factory class that loads indexed subreddits in memory
 */
public final class IndexRepository {
    private static final List<Subreddit> SUBREDDITS = new ArrayList<>();

    public static void loadSubreddits() {
        SUBREDDITS.add(new Subreddit("DMAcademy"));
    }

    public static void addSubreddit(final Subreddit subreddit) {
        if (!SUBREDDITS.contains(subreddit))
            SUBREDDITS.add(subreddit);
    }

    public static List<Subreddit> getAllSubreddits() {
        return SUBREDDITS;
    }

    private IndexRepository() {
        // prevent instantiation
    }
}
