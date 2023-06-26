package org.faebie.website.subreddits.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
@JsonDeserialize(as = ImmutableSubmission.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Submission {
    String id();
    @JsonProperty("name") @Value.Default default String submissionId() {
        return id();
    }
    Subreddit subreddit();
    String title();
    @JsonProperty("selftext") String text();
    String author();
    @JsonProperty("created_utc") long created(); // Time created in UTC
    String permalink(); // "/r/<SUBREDDIT>/comments/<SUBMISSION_ID>/this_is_a_submission_permalink/"
    int score();
    String url();
}
