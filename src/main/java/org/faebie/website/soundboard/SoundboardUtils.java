package org.faebie.website.soundboard;

public final class SoundboardUtils {

    /**
     * Extracts the YouTube video id from the user-input URL on the soundboard form
     * @param url ex: youtube.com/watch?v=VIDEO_ID
     * @return ex: VIDEO_ID
     */
    public static String extractVideoId(final String url) {
        final String[] splitUrl = url.split("watch\\?v\\=");
        if (splitUrl.length == 1) {
            return splitUrl[0];
        }

        return splitUrl[1];
    }

    private SoundboardUtils() {
        // prevent instantiation
    }
}
