package org.faebie.website;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Main {

    public static void main(final String[] args) throws Exception {
        final Clip clip = AudioSystem.getClip();
        final AudioInputStream stream = AudioSystem.getAudioInputStream(new URL("http://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        clip.open(stream);
        clip.start();
    }
}
