package org.faebie.website.soundboard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: read baeldung.com/thymeleaf-list for binding a list
 */
@Controller
public class SoundboardController {

    private static final Logger log = LogManager.getLogger(SoundboardController.class);
    private static final List<Video> VIDEOS = new ArrayList<>();

    @GetMapping(value = "/soundboard")
    public String displaySoundboard(final Model model) {

        model.addAttribute("video", new Video());
        return "soundboard/soundboard";
    }

    @PostMapping(value = "/soundboard")
    public String updateSoundboard(final Model model,
                                   @ModelAttribute final Video video,
                                   @RequestParam(required = false, value = "action") final String action) {
        if ("Clear".equalsIgnoreCase(action)) {
            VIDEOS.clear();
            return "soundboard/soundboard";
        }

        if ("Add Video".equalsIgnoreCase(action) && !VIDEOS.contains(video)) {
            log.info("Adding {} to video list", video);
            VIDEOS.add(video);
        }

        model.addAttribute("videos", VIDEOS);

        return "soundboard/soundboard";
    }
}
