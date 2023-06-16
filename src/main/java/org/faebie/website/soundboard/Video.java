package org.faebie.website.soundboard;

public class Video {
    private static final String EMBED_PREFIX = "http://www.youtube.com/embed/";
    private String videoUrl;
    private String videoId;
    private String embedUrl;

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
        this.videoId = SoundboardUtils.extractVideoId(videoUrl);
        this.embedUrl = EMBED_PREFIX + videoId;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public String getEmbedUrl() {
        return this.embedUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final Video video = (Video) obj;
        return this.getVideoId().equals(video.getVideoId());
    }
}
