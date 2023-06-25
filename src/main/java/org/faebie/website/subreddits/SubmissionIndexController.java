package org.faebie.website.subreddits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class SubmissionIndexController {
    private static final Logger log = LogManager.getLogger(SubmissionIndexController.class);

    private final IndexService indexService;

    public SubmissionIndexController(final IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/subreddits")
    public String displayIndexedSubreddits(final Model model) throws IOException {
        final List<String> indexedSubreddits = indexService.getIndexedSubreddits();
        model.addAttribute("indexedSubreddits", indexedSubreddits);
        return "subreddits/subreddits";
    }
}
