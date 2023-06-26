package org.faebie.website.subreddits.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.stereotype.Component;

@Component
@Value.Immutable
@JsonDeserialize(as = ImmutableComment.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface Comment {

    String id();
    Subreddit subreddit();
    String author();
    String body();
    @JsonProperty("link_id") String submissionId();
    @JsonProperty("parent_id") String parentId();
    int score();
    @Value.Default default int ups() {
        return 0;
    }
    int controversiality();
    @JsonProperty("created_utc") long created(); // Time created in UTC
}
