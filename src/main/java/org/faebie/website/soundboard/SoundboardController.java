package org.faebie.website.soundboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SoundboardController {

    private static final Logger log = LogManager.getLogger(SoundboardController.class);

    private final Soundboard soundboard;

    public SoundboardController(final Soundboard soundboard) {
        this.soundboard = soundboard;
    }

    @GetMapping(value = "/soundboard")
    public String displaySoundboard(final Model model) {

        model.addAttribute("video", new Video());
        model.addAttribute("soundboard", soundboard);
        return "soundboard/soundboard";
    }

    @PostMapping(value = "/soundboard", params = "add_video")
    public String updateSoundboard(final Model model, @ModelAttribute final Video video) {
        if (!soundboard.getVideos().contains(video)) {
            log.info("Adding {} to video list", video);
            soundboard.addVideo(video);
        }

        model.addAttribute("soundboard", soundboard);
        return "soundboard/soundboard";
    }

    @PostMapping(value = "/soundboard", params = "clear_soundboard")
    public String clearSoundboard(final Model model, @ModelAttribute final Video video) {
        soundboard.clearVideos();
        model.addAttribute("soundboard", soundboard);
        return "soundboard/soundboard";
    }
}
