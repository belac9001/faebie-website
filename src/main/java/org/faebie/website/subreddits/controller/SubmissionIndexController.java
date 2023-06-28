package org.faebie.website.subreddits.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.faebie.website.subreddits.IndexRepository;
import org.faebie.website.subreddits.IndexService;
import org.faebie.website.subreddits.submission.submissionimport.SubmissionImportDTO;
import org.faebie.website.subreddits.model.Subreddit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubmissionIndexController {
    private static final Logger log = LogManager.getLogger(SubmissionIndexController.class);

    private final IndexService indexService;

    public SubmissionIndexController(final IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/subreddits")
    public String displayIndexedSubreddits(final Model model) {
        final List<Subreddit> indexedSubreddits = IndexRepository.getAllSubreddits();
        final List<SubmissionImportDTO> submissions = indexService.getSubmissionsForSubreddit(new Subreddit("DMAcademy"));
        model.addAttribute("indexedSubreddits", indexedSubreddits);
        return "subreddits/subreddits";
    }
}
