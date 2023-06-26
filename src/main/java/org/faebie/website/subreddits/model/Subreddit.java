package org.faebie.website.subreddits.model;

public class Subreddit {
    private final String name;

    public Subreddit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = name.hashCode() + prime;

        return prime * result + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Subreddit))
            return false;

        final Subreddit other = (Subreddit) obj;

        return other.getName().equalsIgnoreCase(this.name);
    }
}
