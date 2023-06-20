package org.faebie.website.soundboard;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Soundboard {
    final Set<Video> videos;

    public Soundboard() {
        this.videos = new HashSet<>();
    }

    public Set<Video> getVideos() {
        return this.videos;
    }

    public void addVideo(final Video video) {
        videos.add(video);
    }

    public void removeVideo(final Video video) {
        videos.remove(video);
    }

    public void clearVideos() {
        videos.clear();
    }
}
