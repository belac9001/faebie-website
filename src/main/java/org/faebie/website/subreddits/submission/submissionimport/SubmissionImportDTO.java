package org.faebie.website.subreddits.submission.submissionimport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.faebie.website.subreddits.model.Subreddit;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
@JsonDeserialize(as = ImmutableSubmissionImportDTO.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface SubmissionImportDTO {
    @JsonProperty("id") String id();
    @JsonProperty("name") @Value.Default default String submissionId() {
        return id();
    }
    @JsonProperty("subreddit")
    Subreddit subreddit();
    @JsonProperty("title") String title();
    @JsonProperty("selftext") String text();
    @JsonProperty("author") String author();
    @JsonProperty("created_utc") long created(); // Time created in UTC
    @JsonProperty("permalink") String permalink(); // "/r/<SUBREDDIT>/comments/<SUBMISSION_ID>/this_is_a_submission_permalink/"
    @JsonProperty("score") int score();
    @JsonProperty("url") String url();
}
